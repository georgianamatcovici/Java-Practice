package lab3;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String airportName;
    private List<Runway> runwaysAirport=new ArrayList<>();
    public Airport(String airportName) {
        this.airportName = airportName;
    }
    public Airport(String airportName, List<Runway> runwaysAirport) {
        this.airportName = airportName;

    }

    public void setRunwaysAirport(List<Runway> runwaysAirport) {
        this.runwaysAirport = runwaysAirport;
    }
    public void addRunway(Runway runway)
    {
        runwaysAirport.add(runway);
    }

    public List<Runway> getRunwaysAirport() {
        return runwaysAirport;
    }
    public int getNumRunways() {
        return runwaysAirport.size();
    }
}
