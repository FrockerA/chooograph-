import java.util.*;

public class MyGraph<V> {
    private final boolean undirected;
    private final Map<V, List<V>> map;

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
        this.map = new HashMap<>();
    }

    public void addVertex(V v) {
        map.putIfAbsent(v, new LinkedList<>());
    }

    public void addEdge(V source, V dest) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(source).add(dest);

        if (undirected)
            map.get(dest).add(source);
    }

    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).contains(dest);
    }

    public List<V> adjacencyList(V v) {
        return map.get(v);
    }
}
