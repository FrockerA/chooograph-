import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, Boolean> marked;
    private final Map<V, V> edgeTo;
    private final V source;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(MyGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.put(source, true);
        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (V w : graph.adjacencyList(v)) {
                if (!marked.getOrDefault(w, false)) {
                    queue.add(w);
                    marked.put(w, true);
                    edgeTo.put(w, v);
                }
            }
        }
    }

    public boolean hasPathTo(V vertex) {
        return marked.getOrDefault(vertex, false);
    }

    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V x = vertex; x != source; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }
}
