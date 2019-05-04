package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class Main {

    public static final byte M_INIT = 3, C_INIT = 3;
    private static final State.Margin INITIAL_MARGIN = State.Margin.Left;

    private final State initialState, finalState;

    /**
     * A list with all valid boats (operators).
     */
    public static final List<Boat> VALID_BOATS = new ArrayList<>();

    static {
        // fills the list with valid boats according with parameters.
        for (int m = 0; m <= M_INIT; m++) {
            for (int c = 0; c <= C_INIT; c++) {
                Boat boat = new Boat(m, c);
                if (boat.isValid()) {
                    VALID_BOATS.add(boat);
                }
            }
        }
    }

    private Main() {
        // initial state
        initialState = new State(M_INIT, C_INIT, INITIAL_MARGIN);
        // objective
        finalState = new State(M_INIT, C_INIT,
                State.Margin.Left.equals(INITIAL_MARGIN) ? State.Margin.Right : State.Margin.Left);
    }

    private boolean test() {
        Vertex root = new Vertex(new State(3, 3, State.Margin.Left));

        Vertex child = new Vertex(0, 2, State.Margin.Right);

        Edge a = new Edge(new Boat(0, 2), root, child);
        Edge b = new Edge(new Boat(0, 2), child, root);

        return !a.equals(b) && testVertex();
    }

    private boolean testVertex() {
        Vertex vertex = new Vertex(finalState);

        return vertex.equals(finalState);
    }

    private void run() {
        if (!test()) {
            System.out.println("FALHOU NO TESTE");
            return;
        }

        Vertex init = new Vertex(initialState);

        // add initial state
        Graph graph = new Graph(init);
        graph.build();

        Search search = new Search(graph);
        search.deepSearch(init, finalState);
    }

    /**
     * Main function.
     *
     * @param args Command line arguments (ignore).
     */
    public static void main(final String[] args) {
        Main main = new Main();
        main.run();
    }

}
