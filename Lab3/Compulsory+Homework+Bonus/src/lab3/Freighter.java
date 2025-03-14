package lab3;

public class Freighter extends Aircraft implements CargoCapable {
    private double maximumPayload;
    private double wingSpan;

    public Freighter(String modelAircraft, long tailNumber, String tailName, double maximumPayload, double wingSpan) {
        super(modelAircraft, tailNumber, tailName);
        this.maximumPayload = maximumPayload;
        this.wingSpan = wingSpan;
    }

    @Override
    public double getMaximumPayload() {
        return maximumPayload;
    }
}
