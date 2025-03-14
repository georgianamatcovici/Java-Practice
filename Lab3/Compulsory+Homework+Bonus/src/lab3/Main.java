package lab3;

import java.time.LocalTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Aircraft myfirstAirliner=new Airliner("ATR72", 1234l, "tail1", 170l, 34.5);
        Aircraft myFirstFreighter=new Freighter("Boeing 717", 1235l, "tail2", 10000l, 71.3);
        Aircraft myDrone=new Drone("DJI Air", 1234l, "tail4", 120);
        Aircraft mySecondFreighter=new Freighter("Boeing 715", 1236l, "tail5", 15000l, 72.8);
        Aircraft mySecondAirliner=new Airliner("B738", 1234l, "tail7", 150l, 32.5);
        Aircraft[] transportGoods =new Aircraft[]{myFirstFreighter, mySecondFreighter};

        Flight firstFlight=new Flight(1, new TimeInterval(LocalTime.of(10, 15), LocalTime.of(10, 45)));
        firstFlight.setAssignedAircraft(myfirstAirliner);
        Flight secondFlight=new Flight(2, new TimeInterval(LocalTime.of(11, 15), LocalTime.of(11, 45)));
        secondFlight.setAssignedAircraft(myFirstFreighter);
        Flight thirdFlight=new Flight(3, new TimeInterval(LocalTime.of(10, 0), LocalTime.of(10, 30)));
        thirdFlight.setAssignedAircraft(myDrone);
        Flight fourthFlight=new Flight(4, new TimeInterval(LocalTime.of(11, 5), LocalTime.of(11, 35)));
        fourthFlight.setAssignedAircraft(mySecondFreighter);
        Flight fifthFlight=new Flight(5, new TimeInterval(LocalTime.of(10, 40), LocalTime.of(11, 0)));
        fifthFlight.setAssignedAircraft(mySecondAirliner);
        Flight sixthFlight=new Flight(6, new TimeInterval(LocalTime.of(12, 0), LocalTime.of(12, 30)));
        sixthFlight.setAssignedAircraft(myFirstFreighter);
//        Flight seventhFlight=new Flight(7, new TimeInterval(LocalTime.of(11, 10), LocalTime.of(11, 30)));
//        seventhFlight.setAssignedAircraft(myDrone);

        Runway firstRunway=new Runway(1);
        Runway secondRunway=new Runway(2);

        Airport firstAirport=new Airport("Henri Coanda");
        firstAirport.addRunway(firstRunway);
        firstAirport.addRunway(secondRunway);

        Schedule scheduleProblem=new Schedule();
        scheduleProblem.setMyAirport(firstAirport);
        scheduleProblem.addFlight(firstFlight);
        scheduleProblem.addFlight(secondFlight);
        scheduleProblem.addFlight(thirdFlight);
        scheduleProblem.addFlight(fourthFlight);
        scheduleProblem.addFlight(fifthFlight);
        scheduleProblem.addFlight(sixthFlight);
//        scheduleProblem.addFlight(seventhFlight);

        scheduleProblem.printFlights();
        System.out.println();
        scheduleProblem.printSolution();

    }
}