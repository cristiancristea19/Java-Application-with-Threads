package Model;
public class Client {
    private int Id;
    private int arrivalTime;
    private int serviceTime;

    public Client(int Id, int arrivalTime, int serviceTime)
    {
        this.Id = Id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void decrementServiceTime(){
        serviceTime--;
    }
    @Override
    public String toString()
    {
        return "(" + Id + ", " + arrivalTime + ", " + serviceTime + ')';
    }
}
