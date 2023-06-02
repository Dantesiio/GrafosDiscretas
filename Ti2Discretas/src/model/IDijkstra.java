package model;

import java.util.Map;

public interface IDijkstra<T> {
    void addVertex(T vertex);
    void addEdge(T source, T destination, double weight);
    Map<T, Double> dijkstra(T start);

}
