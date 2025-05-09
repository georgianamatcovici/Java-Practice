package org.example.graphs;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.connectivity.TarjanBiconnectivity;
import org.graph4j.util.Block;

import java.util.List;

public class GraphClass {
    Graph graph;
    public GraphClass()
    {
       graph= GraphBuilder.empty().buildGraph();

    }
    public void addVertex(int u)
    {
        graph.addVertex(u);
    }

    public  void addEdge(int u, int v)
    {
        graph.addEdge(u,v);
    }

    public List<Block> findTwoConnected()
    {
        var biconnectivity = new TarjanBiconnectivity(graph);
        List<Block> blocks = biconnectivity.getBlocks();
        return blocks;

    }


}
