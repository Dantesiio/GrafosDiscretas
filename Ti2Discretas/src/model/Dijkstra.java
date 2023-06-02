package model;

import java.util.*;

public class Dijkstra<T> implements IDijkstra<T> {
    public Map<T, Map<T, Double>> adjacencyMap;

    /**
     * Constructor de la clase Dijkstra.
     * Inicializa el mapa de adyacencia vacío.
     */
    public Dijkstra() {
        adjacencyMap = new HashMap<>();
    }

    /**
     * Obtiene el mapa de adyacencia del grafo.
     *
     * @return Mapa de adyacencia del grafo.
     */
    public Map<T, Map<T, Double>> getAdjacencyMap() {
        return adjacencyMap;
    }

    /**
     * Establece el mapa de adyacencia del grafo.
     *
     * @param adjacencyMap Mapa de adyacencia del grafo.
     */
    public void setAdjacencyMap(Map<T, Map<T, Double>> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }

    /**
     * Agrega un nuevo vértice al grafo.
     *
     * @param vertex Vértice a agregar.
     */
    public void addVertex(T vertex) {
        adjacencyMap.put(vertex, new HashMap<>());
    }

    /**
     * Agrega una nueva arista al grafo entre dos vértices dados con un peso determinado.
     *
     * @param source      Vértice de origen de la arista.
     * @param destination Vértice de destino de la arista.
     * @param weight      Peso de la arista.
     */
    public void addEdge(T source, T destination, double weight) {
        if (adjacencyMap.get(source) == null || adjacencyMap.get(destination) == null) {
            System.out.println("Places do not exist");
        } else {
            adjacencyMap.get(source).put(destination, weight);
            adjacencyMap.get(destination).put(source, weight);
        }
    }

    /**
     * Aplica el algoritmo de Dijkstra para encontrar las distancias más cortas desde un vértice de inicio hasta todos los demás vértices.
     *
     * @param start Vértice de inicio.
     * @return Mapa que contiene los vértices y sus distancias más cortas desde el vértice de inicio.
     */
    public Map<T, Double> dijkstra(T start) {
        Map<T, Double> distances = new HashMap<>();
        for (T vertex : adjacencyMap.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start,  0.0);

        Set<T> visited = new HashSet<>();
        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        queue.add(new Node<>(start, 0));

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            T currentVertex = currentNode.getVertex();
            double currentDistance = currentNode.getweight();

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            Map<T, Double> neighbors = adjacencyMap.get(currentVertex);
            for (T neighbor : neighbors.keySet()) {
                double weight = neighbors.get(neighbor);
                double newDistance = currentDistance + weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    queue.add(new Node<>(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    /**
     * Busca un vértice en el grafo y devuelve su referencia.
     *
     * @param vertex Vértice a buscar.
     * @return Referencia al vértice encontrado o null si no se encuentra.
     */
    public T searchVertex(T vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            return vertex;
        } else {
            for (T v : adjacencyMap.keySet()) {
                if (v.equals(vertex)) {
                    return v;
                }
            }
        }
        return null;
    }

    /**
     * Realiza un recorrido en anchura (BFS) desde un vértice de inicio hasta un vértice de destino.
     * No tiene en cuenta el peso de las aristas.
     *
     * @param start       Vértice de inicio.
     * @param destination Vértice de destino.
     * @return Lista de vértices que representan el camino desde el vértice de inicio hasta el vértice de destino.
     *         Si no se encuentra un camino, devuelve null.
     */
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

            Map<T, Double> neighbors = adjacencyMap.get(current);
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

    /**
     * Reconstruye el camino desde un vértice de inicio hasta un vértice de destino utilizando un mapa de padres.
     *
     * @param start       Vértice de inicio.
     * @param destination Vértice de destino.
     * @param parentMap   Mapa que contiene los padres de cada vértice en el camino.
     * @return Lista de vértices que representan el camino desde el vértice de inicio hasta el vértice de destino.
     *         Si el destino no es alcanzable desde el inicio, devuelve null.
     */
    private List<T> reconstructPath(T start, T destination, Map<T, T> parentMap) {
        List<T> path = new ArrayList<>();
        T current = destination;
        while (current != null && parentMap.containsKey(current)) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        if (current == null) {
            // El destino no es alcanzable desde el inicio
            return null;
        }
        path.add(0, start);
        return path;
    }

    /**
     * Obtiene el camino más corto desde un vértice de inicio hasta un vértice de destino utilizando el algoritmo de Dijkstra.
     * Tiene en cuenta el peso de las aristas.
     *
     * @param start       Vértice de inicio.
     * @param destination Vértice de destino.
     * @return Lista de vértices que representan el camino más corto desde el vértice de inicio hasta el vértice de destino.
     */
    public List<T> getShortestPathDj(T start, T destination) {
        Map<T, Double> distances = new HashMap<>();
        Map<T, T> parentMap = new HashMap<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyMap.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);

        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        queue.add(new Node<>(start, 0));

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            T currentVertex = currentNode.getVertex();
            double currentDistance = currentNode.getweight();

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            Map<T, Double> neighbors = adjacencyMap.get(currentVertex);
            for (T neighbor : neighbors.keySet()) {
                double weight = neighbors.get(neighbor);
                double newDistance = currentDistance + weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    parentMap.put(neighbor, currentVertex);
                    queue.add(new Node<>(neighbor, newDistance));
                }
            }
        }

        return reconstructPath(start, destination, parentMap);
    }

    /**
     * Imprime el grafo en la consola mostrando los vértices y sus conexiones.
     */
    public void printGraph() {
        for (T vertex : adjacencyMap.keySet()) {
            System.out.print("<<<" + vertex + ">>> con escalas a: ");
            Map<T, Double> neighbors = adjacencyMap.get(vertex);
            for (T neighbor : neighbors.keySet()) {
                System.out.print(neighbor + ", ");
            }
            System.out.println();
        }
    }

    /**
     * Elimina un vértice del grafo, junto con todas sus aristas adyacentes.
     *
     * @param vertex Vértice a eliminar.
     * @return true si se eliminó el vértice correctamente, false si el vértice no existe en el grafo.
     */
    public boolean removeVertex(T vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            // Eliminar el vértice y sus aristas de los mapas de adyacencia de otros vértices
            adjacencyMap.remove(vertex);
            for (T v : adjacencyMap.keySet()) {
                adjacencyMap.get(v).remove(vertex);
            }
            return true;
        } else {
            return false;
        }
    }
}
