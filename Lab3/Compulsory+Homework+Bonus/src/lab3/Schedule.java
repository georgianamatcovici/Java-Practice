package lab3;

import java.time.LocalTime;
import java.util.*;

public class Schedule {
    private Airport myAirport;
    private Set<Flight> flightsSet;
    private Map<Flight, Runway> flightMap = new HashMap<>();
    private Graph myGraph;

    public static class SortComparator implements Comparator<Flight> {
        @Override
        public int compare(Flight firstFlight, Flight secondFlight) {
            LocalTime firstTime = firstFlight.getLandingInterval().getSecondElement();
            LocalTime secondTime = secondFlight.getLandingInterval().getSecondElement();
            return firstTime.compareTo(secondTime);
        }
    }

    public Schedule(Airport myAirport, Set<Flight> flightsSet) {
        this.myAirport = myAirport;
        this.flightsSet = flightsSet;
        flightsSet = new TreeSet<>(new SortComparator());
    }

    public Schedule() {
        flightsSet = new TreeSet<>(new SortComparator());
    }

    public void addFlight(Flight flight) {
        flightsSet.add(flight);
    }

    public Airport getMyAirport() {
        return myAirport;
    }

    public void setMyAirport(Airport myAirport) {
        this.myAirport = myAirport;
    }

    public void printFlights() {
        for (Flight flight : flightsSet) {
            System.out.println(flight.getLandingInterval().getFirstElement() + " " + flight.getLandingInterval().getSecondElement());
        }
    }

    public void solveSchedule() {
        myGraph=new Graph(flightsSet.size(), myAirport.getNumRunways());
        List<Runway> runwayList = myAirport.getRunwaysAirport();
        for (Flight flight : flightsSet) {
           // System.out.println(flight.getIdNumber());
            HERE:
            for (Runway runway : runwayList) {
                boolean hasConflict = false;
                HERE1:
                for (Map.Entry<Flight, Runway> mapElement : flightMap.entrySet()) {
                    if (mapElement.getValue().equals(runway)) {
                        Flight assignedFlight = mapElement.getKey();
                        if (!(flight.getLandingInterval().getSecondElement().isBefore(assignedFlight.getLandingInterval().getFirstElement()) || flight.getLandingInterval().getFirstElement().isAfter(assignedFlight.getLandingInterval().getSecondElement()))) {
                            hasConflict = true;
                            myGraph.setEdge(flight.getIdNumber()-1, assignedFlight.getIdNumber()-1);
                           break HERE1;

                        }
                     //   System.out.println(flight.getLandingInterval().getSecondElement()+" "+assignedFlight.getLandingInterval().getFirstElement()+" "+flight.getLandingInterval().getFirstElement()+" "+assignedFlight.getLandingInterval().getSecondElement()+" "+hasConflict);

                    }
                }
                if (!hasConflict) {
                    flightMap.put(flight, runway);
                   //System.out.println(flight.getIdNumber()+" "+runway.getRunwayId());
                     System.out.println("Flight #"+flight.getIdNumber()+" assigned to runway #"+runway.getRunwayId());
                    break HERE;
                }
               // System.out.println("Flight #"+flight.getIdNumber()+" assigned to runway #"+runway.getRunwayId()+" "+hasConflict);
            }

        }
    }


    public void printSolution() {
this.solveSchedule();
for(Map.Entry<Flight, Runway> mapElement : flightMap.entrySet()) {
    System.out.println("Flight: " + mapElement.getKey().getIdNumber() + ", Runway: " + mapElement.getValue().getRunwayId());
System.out.println();
//myGraph.printMatrix();
}
        myGraph.printColoring();
    }
}