package lab3;

public class Drone extends Aircraft {
    int batteryLife;

    public Drone(String modelAircraft, long tailNumber, String tailName, int batteryLife) {
        super(modelAircraft, tailNumber, tailName);
        this.batteryLife = batteryLife;
    }
}
