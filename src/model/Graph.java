package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 10.
 */
public class Graph {

    private final List<Edge> edges = new ArrayList<>();
    private final List<Vertice> vertices = new ArrayList<>();

    public Graph() {
        // empty
    }

    public void addVertice(final Vertice vertice) {
        if (!vertices.contains(vertice)) {
            vertices.add(vertice);
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void addEdge(final Edge edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);

            addVertice(edge.getOrigin());
            addVertice(edge.getDest());
        }
    }

}
