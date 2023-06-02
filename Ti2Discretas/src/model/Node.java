package model;

class Node<T> implements Comparable<Node<T>> {
    private T vertex;
    private double weight;




    public Node(T vertex, double weight) {
        this.vertex = vertex;
        this.weight = weight;

    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }



    public T getVertex() {
        return vertex;
    }

    public double getweight() {
        return weight;
    }

    @Override
    public int compareTo(Node<T> other) {
        return Double.compare(weight, other.weight);
    }
}