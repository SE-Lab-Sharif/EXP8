package org.example.AdapterImplementation.Graph;

import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.ArrayList;
import java.util.List;

public class GraphAdapter<V> implements Graph<V> {
    private final SparseMultigraph<V, String> graph;
    private int edgeCount = 0;

    public GraphAdapter() {
        this.graph = new SparseMultigraph<>();
    }

    @Override
    public void addVertex(V vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void addEdge(String name, V source, V destination) {
        if (graph.containsEdge(name)) {
            throw new IllegalArgumentException("Edge with name " + name + " already exists");
        } else if (name == null) {
            name = "Edge" + edgeCount++;
        }
        graph.addEdge(name, source, destination);
    }

    @Override
    public List<V> getNeighbors(V vertex) {
        return new ArrayList<>(graph.getNeighbors(vertex));
    }
}
