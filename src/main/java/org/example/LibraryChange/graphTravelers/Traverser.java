package org.example.LibraryChange.graphTravelers;

import java.util.List;

public interface Traverser<V> {
    List<V> traverse(V startVertex);
}
