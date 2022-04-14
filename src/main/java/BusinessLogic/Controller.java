package BusinessLogic;
import GUIandLogger.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;

    public Controller(View view)
    {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int nbOfClients = 0;
        int nbOfQueues = 0;
        int tMaxSimulation = 0;
        int tMinArrival = 0;
        int tMaxArrival = 0;
        int tMinService = 0;
        int tMaxService = 0;
        try {
            nbOfClients = Integer.parseInt(view.getClientsNumber());
            if (nbOfClients < 0) {
                view.showMessage("Invalid input for number of clients. Please enter a positive number.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for number of clients. Please enter a positive number");
            return;
        }
        try {
            nbOfQueues = Integer.parseInt(view.getQueuesNumber());
            if (nbOfQueues < 0 || nbOfQueues > 10) {
                view.showMessage("Invalid input for number of queues. Please enter a positive number smaller than 11.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for number of queues. Please enter a positive number");
            return;
        }
        try {
            tMaxSimulation = Integer.parseInt(view.getTMaxSimulation());
            if (tMaxSimulation < 0) {
                view.showMessage("Invalid input for simulation interval. Please enter a positive number.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for simulation interval. Please enter a positive number");
            return;
        }
        try {
            tMinArrival = Integer.parseInt(view.getTMinArrival());
            if (tMinArrival < 0 || tMinArrival > tMaxSimulation) {
                view.showMessage("Invalid input for minimum arrival time. Please enter a positive number smaller than simulation interval.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for minimum arrival time. Please enter a positive number.");
            return;
        }
        try {
            tMaxArrival = Integer.parseInt(view.getTMaxArrival());
            if (tMaxArrival < 0 || tMaxArrival < tMinArrival) {
                view.showMessage("Invalid input for minimum arrival time. Please enter a positive number smaller than maximum arrival time.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for minimum arrival time. Please enter a positive number.");
            return;
        }
        try {
            tMinService = Integer.parseInt(view.getTMinService());
            if (tMinService < 0) {
                view.showMessage("Invalid input for minimum service time. Please enter a positive number.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for minimum service time. Please enter a positive number.");
            return;
        }
        try {
            tMaxService = Integer.parseInt(view.getTMaxService());
            if (tMaxService < 0 || tMaxService < tMinService) {
                view.showMessage("Invalid input for maximum service time. Please enter a positive number bigger than minimum service time.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Invalid input for maximum service time. Please enter a positive number.");
            return;
        }
        SimulationManager simulationManager = new SimulationManager(nbOfClients, nbOfQueues, tMaxSimulation, tMinArrival, tMaxArrival, tMinService, tMaxService, view);
        Thread thread = new Thread(simulationManager);
        thread.start();
    }
}
