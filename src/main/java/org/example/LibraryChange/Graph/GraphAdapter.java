package org.example.LibraryChange.Graph;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphAdapter<V> implements Graph<V> {
    private final SimpleDirectedGraph<V, String> graph;
    private int edgeCount = 0;

    public GraphAdapter() {
        this.graph = new SimpleDirectedGraph<>(String.class);
    }

    @Override
    public void addVertex(V vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void addEdge(String name, V source, V destination) {
        if (graph.containsEdge(name)) {
            throw new IllegalArgumentException("Edge with name " + name + " already exists");
        }
        if (name == null) {
            name = "Edge" + edgeCount++;
        }
        graph.addEdge(source, destination, name);
    }

    @Override
    public List<V> getNeighbors(V vertex) {
        Set<String> outgoingEdges = graph.outgoingEdgesOf(vertex);
        Set<String> incomingEdges = graph.incomingEdgesOf(vertex);
        List<V> neighbors = new ArrayList<>();
        for (String edge : outgoingEdges) {
            neighbors.add(graph.getEdgeTarget(edge));
        }
        for (String edge : incomingEdges) {
            neighbors.add(graph.getEdgeSource(edge));
        }
        return neighbors;
    }
}
