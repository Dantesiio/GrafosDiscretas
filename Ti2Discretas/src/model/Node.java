package model;

class Node<T> implements Comparable<Node<T>> {
    private T vertex;
    private int distance;

    public Node(T vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public T getVertex() {
        return vertex;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node<T> other) {
        return Integer.compare(distance, other.distance);
    }
}