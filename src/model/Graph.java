package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 10.
 */
public class Graph {

    private final Vertex first;

    private final List<Edge> edges = new ArrayList<>();
    private final List<Vertex> vertices = new ArrayList<>();

    public Graph(final Vertex first) {
        this.first = first;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void build() {
        add(first);
    }

    private void add(final Vertex v) {
        if (!vertices.contains(v) && v != null) {
            vertices.add(v);

            Iterator<Map.Entry<Boat, Vertex>> iterator = v.getNeighbors().entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Boat, Vertex> next = iterator.next();

                Vertex child = next.getValue();

                Edge edge = new Edge(next.getKey(), v, child);
                if (!edges.contains(edge)) {
                    edges.add(edge);
                }
                add(child);
            }
        }
    }

    /**
     * Gets all edges that contains given paramater as origin.
     *
     * @param origin Given origin
     * @return A list with all valid edges.
     */
    public List<Edge> getEdges(final Vertex origin) {
        List<Edge> list = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getOrigin().equals(origin)) {
                list.add(e);
            }
        }
        return list;
    }
}
