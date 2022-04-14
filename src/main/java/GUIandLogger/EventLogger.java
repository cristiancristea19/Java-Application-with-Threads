package GUIandLogger;
import java.io.*;



public class EventLogger {
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    public EventLogger()
    {
        createFile();
    }

    public void createFile()
    {
        file = new File("EventLogger.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    public void writeToFile(int simulationTime, String[] queues, String waitingClients)
    {
        try {
            bufferedWriter.write("Time: " + simulationTime + "\n");
            bufferedWriter.write("Waiting clients: ");
            bufferedWriter.write(waitingClients);
            bufferedWriter.write("\n");
            int i=1;
            for (String q: queues) {
                bufferedWriter.write("Queue " + i + ": "+ q + "; \n");
                i++;
            }
            bufferedWriter.write("\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAvgTime(double avg)
    {
        try{
            bufferedWriter.write("Average waiting time: " + avg);
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
