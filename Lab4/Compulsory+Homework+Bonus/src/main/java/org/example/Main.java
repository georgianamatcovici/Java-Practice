package org.example;

import com.github.javafaker.Faker;


import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Problem myProblem=new Problem();
        Faker myFaker = new Faker();
        var myLocations= IntStream.rangeClosed(0, 6)
                .mapToObj(i->new Location(i+1, myFaker.address().fullAddress()))
                .toArray(Location[]::new);
        myLocations[0].setLocationType(LocationType.FRIENDLY);
        myLocations[1].setLocationType(LocationType.ENEMY);
        myLocations[2].setLocationType(LocationType.FRIENDLY);
        myLocations[3].setLocationType(LocationType.NEUTRAL);
        myLocations[4].setLocationType(LocationType.FRIENDLY);
        myLocations[5].setLocationType(LocationType.ENEMY);


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

        System.out.println("Friend locations: ");
        myFriendlyLocations.stream()
                        .forEach(myLoc->System.out.println(myLoc.getLocationId()+" "+myLoc.getLocationName()));
        System.out.println();

        System.out.println("Enemy locations: ");
        myEnemyLocations.stream()
                .forEach(myLoc->System.out.println(myLoc.getLocationId()+" "+myLoc.getLocationName()));


//        ArrayList<Location> locationList=new ArrayList<>(Arrays.asList(myLocations));
        Arrays.stream(myLocations).forEach(myLoc->{if(myLoc!=null) myProblem.addLocation(myLoc);});


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


        System.out.println();
        myProblem.printRoutes();
        System.out.println();


        Map<LocationType, List<Location>> locationMap =
                Arrays.stream(myLocations)
                        .filter(myElem->myElem.getLocationType()!=null)
                        .collect(
                        Collectors.groupingBy(Location::getLocationType)
                );

        System.out.println("Friend locations: ");
        locationMap.entrySet()
                .stream()
                .filter(myElem->myElem.getKey()==LocationType.FRIENDLY)
                        .forEach(myElem->{
                            List<Location> friendlyLocations=myElem.getValue();
                            friendlyLocations.stream().forEach(myProblem::solveDijkstra);
                        });

        System.out.println("Neutral locations: ");
        locationMap.entrySet()
                .stream()
                .filter(myElem->myElem.getKey()==LocationType.NEUTRAL)
                .forEach(myElem->{
                    List<Location> friendlyLocations=myElem.getValue();
                    friendlyLocations.stream().forEach(myProblem::solveDijkstra);
                });

        System.out.println("Enemy locations: ");
        locationMap.entrySet()
                .stream()
                .filter(myElem->myElem.getKey()==LocationType.ENEMY)
                .forEach(myElem->{
                    List<Location> friendlyLocations=myElem.getValue();
                    friendlyLocations.stream().forEach(myProblem::solveDijkstra);
                });

        myProblem.modifyCosts();
        System.out.println();
        myProblem.solveFloydWarshall();
    }


}