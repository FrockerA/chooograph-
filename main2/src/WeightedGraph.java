import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<Vertex<V>, List<Edge<V>>> map;

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.map = new HashMap<>();
    }

    public void addVertex(V v) {
        map.putIfAbsent(new Vertex<>(v), new ArrayList<>());
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> v1 = new Vertex<>(source);
        Vertex<V> v2 = new Vertex<>(dest);
        map.putIfAbsent(v1, new ArrayList<>());
        map.putIfAbsent(v2, new ArrayList<>());
        map.get(v1).add(new Edge<>(v1, v2, weight));
        if (undirected) {
            map.get(v2).add(new Edge<>(v2, v1, weight));
        }
    }

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        return map.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<Vertex<V>> getVertices() {
        return map.keySet();
    }
}
