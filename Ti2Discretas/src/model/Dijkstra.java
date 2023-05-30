package model;

import java.util.*;

public class Dijkstra<T> implements IDijkstra<T> {
    private Map<T, Map<T, Integer>> adjacencyMap;

    public Dijkstra() {
        adjacencyMap = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyMap.put(vertex, new HashMap<>());
    }

    public void addEdge(T source, T destination, int weight) {
        if (adjacencyMap.get(source) == null || adjacencyMap.get(destination) == null) {
            System.out.println("Places do not exist");
        } else {
            adjacencyMap.get(source).put(destination, weight);
            adjacencyMap.get(destination).put(source, weight);
        }
    }

    public Map<T, Integer> dijkstra(T start) {
        Map<T, Integer> distances = new HashMap<>();
        for (T vertex : adjacencyMap.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        Set<T> visited = new HashSet<>();
        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        queue.add(new Node<>(start, 0));

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            T currentVertex = currentNode.getVertex();
            int currentDistance = currentNode.getDistance();

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            Map<T, Integer> neighbors = adjacencyMap.get(currentVertex);
            for (T neighbor : neighbors.keySet()) {
                int weight = neighbors.get(neighbor);
                int newDistance = currentDistance + weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    queue.add(new Node<>(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    public List<T> bfs(T start, T destination) {
        Queue<T> queue = new LinkedList<>();
        Map<T, T> parentMap = new HashMap<>();
        Set<T> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();

            if (current.equals(destination)) {
                // Se ha encontrado el destino, reconstruir el camino y retornarlo
                return reconstructPath(start, destination, parentMap);
            }

            Map<T, Integer> neighbors = adjacencyMap.get(current);
            for (T neighbor : neighbors.keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // No se encontró un camino desde el inicio hasta el destino
        return null;
    }

    private List<T> reconstructPath(T start, T destination, Map<T, T> parentMap) {
        List<T> path = new ArrayList<>();
        T current = destination;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        return path;
    }




    public List<T> getShortestPathDj(T start, T destination) {
        Map<T, Integer> distances = new HashMap<>();
        Map<T, T> parentMap = new HashMap<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyMap.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        queue.add(new Node<>(start, 0));

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            T currentVertex = currentNode.getVertex();
            int currentDistance = currentNode.getDistance();

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            Map<T, Integer> neighbors = adjacencyMap.get(currentVertex);
            for (T neighbor : neighbors.keySet()) {
                int weight = neighbors.get(neighbor);
                int newDistance = currentDistance + weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    parentMap.put(neighbor, currentVertex);
                    queue.add(new Node<>(neighbor, newDistance));
                }
            }
        }

        return reconstructPath(start, destination, parentMap);
    }



    public void printGraph() {
        for (T vertex : adjacencyMap.keySet()) {
            System.out.print("<<<" + vertex + ">>> con escalas a: ");
            Map<T, Integer> neighbors = adjacencyMap.get(vertex);
            for (T neighbor : neighbors.keySet()) {
                System.out.print(neighbor + ", ");
            }
            System.out.println();
        }
    }
}