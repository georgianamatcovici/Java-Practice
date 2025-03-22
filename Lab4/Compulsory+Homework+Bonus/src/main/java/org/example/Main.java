package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Problem myProblem=new Problem();
        Location[] myLocations = new Location[6];
        Faker myFaker = new Faker();
        myLocations[0] = new Location(1, myFaker.address().fullAddress(), LocationType.FRIENDLY);
        myLocations[1] = new Location(2, myFaker.address().fullAddress(), LocationType.ENEMY);
        myLocations[2] = new Location(3, myFaker.address().fullAddress(), LocationType.FRIENDLY);
        myLocations[3] = new Location(4, myFaker.address().fullAddress(), LocationType.NEUTRAL);
        myLocations[4] = new Location(5, myFaker.address().fullAddress(), LocationType.FRIENDLY);
        myLocations[5] = new Location(6, myFaker.address().fullAddress(), LocationType.ENEMY);


        TreeSet<Location> myFriendlyLocations= Arrays.stream(myLocations)
                .filter(myElem->myElem!=null && myElem.getLocationType()==LocationType.FRIENDLY)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toCollection(TreeSet::new));


        LinkedList<Location> myEnemyLocations = Arrays.stream(myLocations)
                .filter(myElem->myElem!=null && myElem.getLocationType()==LocationType.ENEMY)
                .sorted((o1, o2) -> {
                    if (o1.getLocationType() == o2.getLocationType()) {
                        return o1.getLocationName().compareTo(o2.getLocationName());
                    } else
                        return o1.getLocationType().compareTo(o2.getLocationType());
                })
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Location> myNeutralLocations=Arrays.stream(myLocations)
                .filter(myElem->myElem!=null && myElem.getLocationType()==LocationType.NEUTRAL)
                .collect(Collectors.toCollection(LinkedList::new));


        for(Location myLoc : myLocations) {
            if(myLoc!=null) myProblem.addLocation(myLoc);
        }

        myProblem.addRoute(myLocations[0], myLocations[1], new Route(7, 0.6));
        myProblem.addRoute(myLocations[0], myLocations[2], new Route(9, 0.5));
        myProblem.addRoute(myLocations[0], myLocations[5], new Route(14, 0.35));
        myProblem.addRoute(myLocations[1], myLocations[2], new Route(10, 0.2));
        myProblem.addRoute(myLocations[1], myLocations[3], new Route(15, 0.5));
        myProblem.addRoute(myLocations[2], myLocations[5], new Route(2, 0.3));
        myProblem.addRoute(myLocations[2], myLocations[3], new Route(11, 0.7));
        myProblem.addRoute(myLocations[3], myLocations[4], new Route(6, 0.4));
        myProblem.addRoute(myLocations[4], myLocations[5], new Route(9, 0.23));
        myProblem.setStartLocation(myLocations[0]);

        System.out.println("Friend locations: ");
        for(Location myLoc : myFriendlyLocations) {
            System.out.println(myLoc.getLocationId()+". "+myLoc.getLocationName());
        }
        System.out.println();
        System.out.println("Enemy locations: ");
        for(Location myLoc : myEnemyLocations) {
            System.out.println(myLoc.getLocationId()+". "+myLoc.getLocationName());
        }
        System.out.println();
        myProblem.printRoutes();
        System.out.println();

        System.out.println("Friend locations: ");
        for(Location myLoc : myFriendlyLocations) {
            myProblem.solveDijkstra(myLoc);
        }

        System.out.println("Neutral locations: ");
        for(Location myLoc : myNeutralLocations) {
            myProblem.solveDijkstra(myLoc);
        }

        System.out.println("Enemy locations: ");
        for(Location myLoc : myEnemyLocations) {
            myProblem.solveDijkstra(myLoc);
        }
        myProblem.modifyCosts();
        System.out.println();
        myProblem.solveFloydWarshall();
    }


}