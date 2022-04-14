package Model;
import BusinessLogic.SimulationManager;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitingQueue implements Runnable{
    private BlockingQueue<Client> queue;
    private AtomicInteger waitingPeriod;
    private final SimulationManager simulationManager;

    public WaitingQueue(SimulationManager simulationManager)
    {
        queue = new LinkedBlockingQueue<Client>();
        waitingPeriod = new AtomicInteger(0);
        this.simulationManager = simulationManager;
    }

    public AtomicInteger getWaitingPeriod()
    {
        return waitingPeriod;
    }

    public void addClient(Client client)
    {
        queue.add(client);
        waitingPeriod.getAndAdd(client.getServiceTime());
    }

    public int getSize()
    {
        return queue.size();
    }

    @Override
    public void run() {
        while(simulationManager.isNotDone()) {
            if (queue.size() != 0){
                queue.peek().decrementServiceTime();
                if(waitingPeriod.get()!=0) waitingPeriod.getAndDecrement();
                if (queue.peek().getServiceTime() == 0) {
                    queue.poll();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String toString()
    {
        Iterator i = queue.iterator();
        String data="";
        while(i.hasNext())
        {
            data += i.next() + " ";
        }
        if(data.equals("")) data="closed";
        return data;
    }
}
