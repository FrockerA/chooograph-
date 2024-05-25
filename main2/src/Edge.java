public class Edge<V> {
    private Vertex<V> source;
    private Vertex<V> dest;
    private Double weight;

    public Edge(Vertex<V> source, Vertex<V> dest, Double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public Vertex<V> getSource() {
        return source;
    }

    public Vertex<V> getDest() {
        return dest;
    }

    public Double getWeight() {
        return weight;
    }
}
