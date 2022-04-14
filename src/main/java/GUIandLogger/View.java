package GUIandLogger;
import BusinessLogic.Controller;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JPanel panel;
    private JLabel titleLbl;
    private JLabel clientsNbLbl;
    private JLabel qsNbLbl;
    private JLabel tMaxSimLbl;
    private JLabel tMinArrivalLbl;
    private JLabel tMaxArrivalLbl;
    private JLabel tMinServiceLbl;
    private JLabel tMaxServiceLbl;
    private JLabel[] qLbls;
    private JLabel waitingClientsLbl;
    private JLabel simTimeLbl;
    private JTextField clientsNbTxtFld;
    private JTextField qsNbTxtFld;
    private JTextField tMaxSimTxtFld;
    private JTextField tMinArrivalTxtFld;
    private JTextField tMaxArrivalTxtFld;
    private JTextField tMinServiceTxtFld;
    private JTextField tMaxServiceTxtFld;
    private JTextField[] qTxtFlds;
    private JTextField waitingClientsTxtFld;
    private JButton startBtn;
    private Controller controller;
    public View()
    {
        super("Queues Management Application");
        controller = new Controller(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(700, 680));
        this.setLocation(500, 100);
        this.setVisible(true);
        panel = new JPanel();
        panel.setLayout(null);
        qLbls = new JLabel[10];
        qTxtFlds = new JTextField[10];
        initLabels();
        initTextFields();
        initButtons();
        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void initLabels()
    {
        titleLbl = new JLabel();
        titleLbl.setFont(new Font("Tahoma", Font.BOLD, 22));
        titleLbl.setBounds(150, 10, 500, 30);
        titleLbl.setText("Queues Management Application");
        panel.add(titleLbl);

        clientsNbLbl = new JLabel();
        clientsNbLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clientsNbLbl.setBounds(70, 50, 200, 28);
        clientsNbLbl.setText("Number of clients:");
        panel.add(clientsNbLbl);

        qsNbLbl = new JLabel();
        qsNbLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        qsNbLbl.setBounds(70, 80, 200, 28);
        qsNbLbl.setText("Number of queues:");
        panel.add(qsNbLbl);

        tMaxSimLbl = new JLabel();
        tMaxSimLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tMaxSimLbl.setBounds(70, 110, 300, 28);
        tMaxSimLbl.setText("Simulation interval(seconds):");
        panel.add(tMaxSimLbl);

        tMinArrivalLbl = new JLabel();
        tMinArrivalLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tMinArrivalLbl.setBounds(70, 140, 300, 28);
        tMinArrivalLbl.setText("Minimum arrival time(seconds):");
        panel.add(tMinArrivalLbl);

        tMaxArrivalLbl = new JLabel();
        tMaxArrivalLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tMaxArrivalLbl.setBounds(70, 170, 300, 28);
        tMaxArrivalLbl.setText("Maximum arrival time(seconds):");
        panel.add(tMaxArrivalLbl);

        tMinServiceLbl = new JLabel();
        tMinServiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tMinServiceLbl.setBounds(70, 200, 300, 28);
        tMinServiceLbl.setText("Minimum service time(seconds):");
        panel.add(tMinServiceLbl);

        tMaxServiceLbl = new JLabel();
        tMaxServiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tMaxServiceLbl.setBounds(70, 230, 300, 28);
        tMaxServiceLbl.setText("Maximum service time(seconds):");
        panel.add(tMaxServiceLbl);

        simTimeLbl = new JLabel();
        simTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        simTimeLbl.setBounds(250, 260, 300, 28);
        simTimeLbl.setForeground(Color.RED);
        simTimeLbl.setText("Simulation time:");
        simTimeLbl.setVisible(false);
        panel.add(simTimeLbl);

        waitingClientsLbl = new JLabel();
        waitingClientsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        waitingClientsLbl.setBounds(30, 300, 300, 28);
        waitingClientsLbl.setText("Waiting clients:");
        waitingClientsLbl.setVisible(false);
        panel.add(waitingClientsLbl);

        for(int i=0; i<10; i++)
        {
            qLbls[i] = new JLabel();
            qLbls[i].setFont(new Font("Tahoma", Font.PLAIN, 16));
            qLbls[i].setBounds(30, 330+i*30, 300, 28);
            qLbls[i].setText("Queue " + (i+1) + " :");
            qLbls[i].setVisible(false);
            panel.add(qLbls[i]);
        }
    }

    public void initTextFields()
    {
        clientsNbTxtFld = new JTextField(10);
        clientsNbTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        clientsNbTxtFld.setBounds(350, 50, 50,27);
        panel.add(clientsNbTxtFld);

        qsNbTxtFld = new JTextField(10);
        qsNbTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        qsNbTxtFld.setBounds(350, 80, 50,27);
        panel.add(qsNbTxtFld);

        tMaxSimTxtFld = new JTextField(10);
        tMaxSimTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tMaxSimTxtFld.setBounds(350, 110, 50,27);
        panel.add(tMaxSimTxtFld);

        tMinArrivalTxtFld = new JTextField(10);
        tMinArrivalTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tMinArrivalTxtFld.setBounds(350, 140, 50,27);
        panel.add(tMinArrivalTxtFld);

        tMaxArrivalTxtFld = new JTextField(10);
        tMaxArrivalTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tMaxArrivalTxtFld.setBounds(350, 170, 50,27);
        panel.add(tMaxArrivalTxtFld);

        tMinServiceTxtFld = new JTextField(10);
        tMinServiceTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tMinServiceTxtFld.setBounds(350, 200, 50,27);
        panel.add(tMinServiceTxtFld);

        tMaxServiceTxtFld = new JTextField(10);
        tMaxServiceTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tMaxServiceTxtFld.setBounds(350, 230, 50,27);
        panel.add(tMaxServiceTxtFld);

        waitingClientsTxtFld = new JTextField(500);
        waitingClientsTxtFld.setFont(new Font("Tahoma", Font.PLAIN, 15));
        waitingClientsTxtFld.setBounds(160, 300, 500,27);
        waitingClientsTxtFld.setEditable(false);
        waitingClientsTxtFld.setVisible(false);
        panel.add(waitingClientsTxtFld);

        for(int i=0; i<10; i++)
        {
            qTxtFlds[i] = new JTextField(500);
            qTxtFlds[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
            qTxtFlds[i].setBounds(160, 330+i*30, 500, 25);
            qTxtFlds[i].setEditable(false);
            qTxtFlds[i].setVisible(false);
            panel.add(qTxtFlds[i]);
        }
    }

    public void initButtons()
    {
        startBtn = new JButton("Start");
        startBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        startBtn.setBounds(470, 130, 150 , 50);
        startBtn.addActionListener(controller);
        panel.add(startBtn);

    }

    public JButton getStartBtn()
    {
        return startBtn;
    }


    public String getClientsNumber()
    {
        return clientsNbTxtFld.getText();
    }

    public String getQueuesNumber()
    {
        return qsNbTxtFld.getText();
    }

    public String getTMaxSimulation()
    {
        return tMaxSimTxtFld.getText();
    }

    public String getTMinArrival()
    {
        return tMinArrivalTxtFld.getText();
    }

    public String getTMaxArrival()
    {
        return tMaxArrivalTxtFld.getText();
    }

    public String getTMinService()
    {
        return tMinServiceTxtFld.getText();
    }

    public String getTMaxService()
    {
        return tMaxServiceTxtFld.getText();
    }

    public void showMessage(String text)
    {
        JOptionPane.showMessageDialog(this, text);
    }

    public void setSimTime(int simulationTime)
    {
        simTimeLbl.setVisible(true);
        simTimeLbl.setText("Simulation time: " + simulationTime);
    }

    public void setWaitingClients(String waitingClients)
    {
        waitingClientsLbl.setVisible(true);
        waitingClientsTxtFld.setVisible(true);
        waitingClientsTxtFld.setText(waitingClients);
    }

    public void setQueues(String[] queues)
    {
        for(int i=0; i<queues.length; i++)
        {
            qLbls[i].setVisible(true);
            qTxtFlds[i].setVisible(true);
            qTxtFlds[i].setText(queues[i]);
        }
    }

    public void setThingsInvisible()
    {
        waitingClientsTxtFld.setVisible(false);
        waitingClientsLbl.setVisible(false);
        simTimeLbl.setVisible(false);
        for(int i=0; i<qTxtFlds.length; i++)
        {
            qTxtFlds[i].setVisible(false);
            qLbls[i].setVisible(false);
        }
    }
}
