package lab3;

public class Airliner extends Aircraft implements PassengerCapable, CargoCapable {
    private int seatCount;
    private double maximumPayload;
    private double wingSpan;

    public Airliner(String modelAircraft, long tailNumber, String tailName, double maximumPayload, double wingSpan) {
        super(modelAircraft, tailNumber, tailName);
        this.maximumPayload = maximumPayload;
        this.wingSpan = wingSpan;
    }

    @Override
    public int getSeatCount(){
        return seatCount;
    }
    public void setSeatCount(int seatCount){
        this.seatCount = seatCount;
    }
    @Override
    public double getMaximumPayload(){
        return maximumPayload;
    }
}
