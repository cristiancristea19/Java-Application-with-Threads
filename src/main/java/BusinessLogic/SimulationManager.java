package BusinessLogic;
import Model.*;
import GUIandLogger.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable {
    private List<WaitingQueue> queues;
    private AtomicInteger simulationTime;
    private AtomicInteger sumOfWaitingTime;
    private final int nbOfClients;
    private final int nbOfQueues;
    private final int tMaxSimulation;
    private final int tMinArrival;
    private final int tMaxArrival;
    private final int tMinService;
    private final int tMaxService;
    private List<Client> waitingClients;
    private EventLogger eventLogger;
    private View view;
    public SimulationManager(int nbOfClients, int nbOfQueues, int tMaxSimulation, int tMinArrival, int tMaxArrival, int tMinService, int tMaxService, View view)
    {
        queues = new ArrayList<WaitingQueue>();
        waitingClients = new ArrayList<Client>();
        this.nbOfClients = nbOfClients;
        this.nbOfQueues = nbOfQueues;
        this.tMaxSimulation = tMaxSimulation;
        this.tMinArrival = tMinArrival;
        this.tMaxArrival = tMaxArrival;
        this.tMinService = tMinService;
        this.tMaxService = tMaxService;
        simulationTime = new AtomicInteger(0);
        sumOfWaitingTime = new AtomicInteger(0);
        generateClients();
        generateQueues();
        this.view = view;
        eventLogger = new EventLogger();
    }

    public void generateClients()
    {
        Random random = new Random();
        for(int i=0; i<nbOfClients; i++)
        {
            int tArrival;
            int tService;
            if(tMinArrival == tMaxArrival)
            {
                tArrival = tMaxArrival;
            }
            else
            {
                tArrival = random.ints(tMinArrival, tMaxArrival).findFirst().getAsInt();
            }
            if(tMinService == tMaxService)
            {
                tService = tMaxService;
            }
            else
            {
                tService = random.ints(tMinService, tMaxService).findFirst().getAsInt();
            }
            Client client = new Client(i+1, tArrival, tService);
            waitingClients.add(client);
        }
        waitingClients.sort(new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                if(c1.getArrivalTime() == c2.getArrivalTime())
                    return c1.getServiceTime() - c2.getServiceTime();
                return c1.getArrivalTime() - c2.getArrivalTime();
            }
        });
    }

    public void generateQueues()
    {
        for(int i=0; i<nbOfQueues; i++)
        {
            WaitingQueue q = new WaitingQueue(this);
            queues.add(q);
            Thread thread = new Thread(q);
            thread.start();
        }
    }

    public WaitingQueue getMinQueue()
    {
        WaitingQueue minQ = queues.get(0);
        for(WaitingQueue q: queues)
        {
            int t1 = q.getWaitingPeriod().get();
            int t2 = minQ.getWaitingPeriod().get();
            if(t1 < t2)
            {
                minQ = q;
            }
        }
        sumOfWaitingTime.getAndAdd(minQ.getWaitingPeriod().get());
        return minQ;
    }

    @Override
    public void run() {
        while(isNotDone())
        {
            Iterator<Client> it = waitingClients.iterator();
            while(it.hasNext())
            {
                Client client = it.next();
                if(client.getArrivalTime() == simulationTime.get())
                {
                    WaitingQueue q = getMinQueue();
                    q.addClient(client);
                    sumOfWaitingTime.getAndAdd(client.getServiceTime());
                    it.remove();
                }
            }
            String[] queuesList = getQueues();
            String waitingClientsList = getWaitingClients();
            view.setSimTime(simulationTime.get());
            view.setWaitingClients(waitingClientsList);
            view.setQueues(queuesList);
            eventLogger.writeToFile(simulationTime.get(), queuesList, waitingClientsList);
            try {
                Thread.sleep(1000);
                simulationTime.getAndIncrement();
            } catch (InterruptedException e) {
            }
        }
        String[] queuesList = getQueues();
        String waitingClientsList = getWaitingClients();
        view.setSimTime(simulationTime.get());
        view.setWaitingClients(waitingClientsList);
        view.setQueues(queuesList);
        double avg = sumOfWaitingTime.get() / (double)nbOfClients;
        eventLogger.writeAvgTime(avg);
        view.showMessage("The simulation has successfully finished! The average waiting time is " + String.format("%.2f.", avg));
        view.setThingsInvisible();
    }

    public String getWaitingClients()
    {
        String waitingClientsList = "";
        for(Client client: waitingClients)
        {
            waitingClientsList += client.toString() + " ";
        }
        return waitingClientsList;
    }

    public String[] getQueues()
    {
        String[] queuesList = new String[queues.size()];
        int i=0;
        for(WaitingQueue q: queues)
        {
            queuesList[i] = q.toString()  + " ";
            i++;
        }
        return queuesList;
    }

    public synchronized boolean isNotDone()
    {
        boolean notDone = false;
        if(waitingClients.size() != 0) notDone = true;
        else {
            for (WaitingQueue q : queues) {
                if (q.getSize() != 0) {
                    notDone = true;
                    break;
                }
            }
        }
        return notDone && (simulationTime.get() <= tMaxSimulation);
    }
}
