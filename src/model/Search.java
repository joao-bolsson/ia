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
        search(v, st);

        return stack;
    }

    private void search(final Vertex v, final State st) {
        if (v.equals(st)) {
            System.out.println("encontrou! " + stack);
            return;
        }

        stack.add(v);

//        System.out.println("pilha: " + stack);
        List<Edge> edges = g.getEdges(v);

//        System.out.println("edges: " + edges);
        for (Edge e : edges) {
            if (!e.isVisited()) {
//                System.out.println("visita: " + e);
                e.setVisited(true);

                search(e.getDest(), st);

                int last = stack.size() - 1;
                if (last >= 0) {
                    stack.remove(last);
                }
            }
        }

    }

}
