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

    private final Vertice first;

    private final List<Edge> edges = new ArrayList<>();
    private final List<Vertice> vertices = new ArrayList<>();

    public Graph(final Vertice first) {
        this.first = first;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void build() {
        add(first);
    }

    private void add(final Vertice v) {
        if (!vertices.contains(v) && v != null) {
            vertices.add(v);

            Iterator<Map.Entry<Boat, Vertice>> iterator = v.getNeighbors().entrySet().iterator();

            System.out.println("adiciona filhos do " + v);
            while (iterator.hasNext()) {
                Map.Entry<Boat, Vertice> next = iterator.next();

                Vertice child = next.getValue();

                System.out.println("filho adc: " + child);

                Edge edge = new Edge(next.getKey(), v, child);
                if (!edges.contains(edge)) {
                    System.out.println("adciona edge: " + edge);
                    edges.add(edge);
                } else {
                    System.out.println("jah contem edge: " + edge);
                }
                add(child);
            }
        }
    }
}
