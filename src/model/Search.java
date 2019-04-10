package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 10.
 */
public class Search {

    private final List<Vertex> stack = new ArrayList<>();

    private final List<Edge> stackEdge = new ArrayList<>();

    private final Graph g;

    public Search(final Graph g) {
        this.g = g;
    }

    /**
     * Deep search
     *
     * @param v Vertex to begin the search.
     * @param st Target state.
     * @return The path.
     */
    public List<Vertex> deepSearch(final Vertex v, final State st) {
        stack.clear();
        List<Vertex> vertices = g.getVertices();

        if (!vertices.contains(v)) {
            return new ArrayList<>();
        }

        System.out.println("==== DEEP SEARCH ===");
        search(v, st, null);

        return stack;
    }

    private void search(final Vertex v, final State st, final Edge edge) {
        if (v.equals(st)) {
            if (!stack.contains(v)) {
                stack.add(v);
            }

            System.out.println("encontrou! " + stack);

            return;
        }

        stack.add(v);

        if (edge != null) {
            edge.setVisited(true);
            stackEdge.add(edge);
        }
        System.out.println("empilhou: " + v);

        List<Edge> edges = g.getEdges(v);

        for (Edge e : edges) {
            boolean mustVisit = true;

            if (edge != null) {

                mustVisit = !e.getDest().equals(edge.getOrigin());
//                System.out.println(e.getDest() + " ori: " + edge.getOrigin());
            }

            if (!e.isVisited() && mustVisit) {
                e.setVisited(true);

                search(e.getDest(), st, e);

                int last = stack.size() - 1;
                if (last >= 0) {
                    stack.remove(last);
                }

                stackEdge.remove(edge);
            }
        }

    }

}
