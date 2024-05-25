import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final Map<V, Boolean> marked;
    private final Map<V, V> edgeTo;
    private final V source;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.source = source;
        dfs(graph, source);
    }

    private void dfs(MyGraph<V> graph, V v) {
        marked.put(v, true);
        for (V w : graph.adjacencyList(v)) {
            if (!marked.getOrDefault(w, false)) {
                edgeTo.put(w, v);
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return marked.getOrDefault(vertex, false);
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return new ArrayList<>();  // Return empty list instead of null
        LinkedList<V> path = new LinkedList<>();
        for (V x = vertex; x != source; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }
}
