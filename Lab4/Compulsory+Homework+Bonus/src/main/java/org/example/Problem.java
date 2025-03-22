package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.Set;

@Getter
@Setter
public class Problem {
    private Location startLocation;

    Graph<Location, Route> graphMap=new SimpleDirectedWeightedGraph<Location, Route>(Route.class);
    public Problem() {
    }
    public void addLocation(Location location) {
        graphMap.addVertex(location);
    }
    public void addRoute(Location startLocation, Location endLocation, Route route) {
        graphMap.addEdge(startLocation, endLocation, route);
       graphMap.setEdgeWeight(route, route.getTimeNeeded());

    }

    public void printRoutes() {
        for (Route route : graphMap.edgeSet()) {
            Location firstLocation = graphMap.getEdgeSource(route);
            Location endLocation = graphMap.getEdgeTarget(route);
            System.out.println(firstLocation.getLocationId() + " -> " + endLocation.getLocationId() + " Time: " + route.getTimeNeeded() + " Probability: " + route.getSafetyProbability());
        }
    }

    public void solveDijkstra(Location currentLocation)
    { int sourceId=startLocation.getLocationId();
        DijkstraShortestPath<Location, Route> dijkstra = new DijkstraShortestPath<>(graphMap);

            GraphPath<Location, Route> fastestRoute = dijkstra.getPath(startLocation, currentLocation);
            if(fastestRoute!=null){
                System.out.println(sourceId+" -> "+currentLocation.getLocationId()+" Route: "+fastestRoute.getVertexList()+ " Total time "+ fastestRoute.getWeight());
            }
            else {
                System.out.println("No route");
            }




    }

    public void modifyCosts()
    {
        for(Route route : graphMap.edgeSet())
        {
            double safetyProbability=route.getSafetyProbability();
            double logProb=-Math.log10(safetyProbability);
            graphMap.setEdgeWeight(route, logProb);
        }
    }

    public void solveFloydWarshall()
    {
        FloydWarshallShortestPaths<Location, Route> floydWarshall = new FloydWarshallShortestPaths<Location, Route>(graphMap);
    System.out.println("Safest routes:");
    for (Location firstLocation : graphMap.vertexSet()) {
        for (Location secondLocation : graphMap.vertexSet()) {
            GraphPath<Location, Route> safestRoute = floydWarshall.getPath(firstLocation, secondLocation);
            if(safestRoute!=null){
            System.out.println("Safest route from "+firstLocation.getLocationId()+" to "+secondLocation.getLocationId()+": "+safestRoute.getVertexList());
            }
            else {
                System.out.println("No route from "+firstLocation.getLocationId()+" to "+secondLocation.getLocationId());
            }
        }
    }
    }

}
