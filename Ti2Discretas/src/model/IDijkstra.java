package model;

import java.util.Map;

public interface IDijkstra<T> {
    void addVertex(T vertex);
    void addEdge(T source, T destination, int weight);
    Map<T, Integer> dijkstra(T start);

}
