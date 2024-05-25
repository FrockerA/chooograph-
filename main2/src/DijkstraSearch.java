import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, Double> distTo;
    private final Map<V, V> edgeTo;
    private final V source;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.source = source;
        dijkstra(graph, source);
    }

    private void dijkstra(WeightedGraph<V> graph, V source) {
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));
        Vertex<V> sourceVertex = new Vertex<>(source);
        distTo.put(source, 0.0);
        pq.add(sourceVertex);

        while (!pq.isEmpty()) {
            Vertex<V> v = pq.poll();
            for (Edge<V> edge : graph.getEdges(v)) {
                V w = edge.getDest().getData();
                double weight = edge.getWeight();
                if (distTo.getOrDefault(w, Double.POSITIVE_INFINITY) > distTo.get(v.getData()) + weight) {
                    distTo.put(w, distTo.get(v.getData()) + weight);
                    edgeTo.put(w, v.getData());
                    pq.add(new Vertex<>(w));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return distTo.containsKey(vertex);
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
