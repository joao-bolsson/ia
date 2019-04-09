package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class Node extends State {

    private final Map<Boat, Node> childs;

    /**
     * Creates a node.
     *
     * @param m Number of missionaries.
     * @param c Number of cannibals.
     * @param margin Margin where is the missionaries and cannibals.
     */
    public Node(final int m, final int c, final Margin margin) {
        super(m, c, margin);

        childs = new HashMap<>();
    }

    /**
     * Creates a node from a given state.
     *
     * @param state Given state to create the node.
     */
    public Node(final State state) {
        this(state.m, state.c, state.margin);
    }

    /**
     * Builds the node with children.
     */
    public void init() {
        initChilds();
    }

    private void initChilds() {
        if (!isValid()) {
            return;
        }
        Margin otherMargin = margin.equals(Margin.Left) ? Margin.Right : Margin.Left;

        for (Boat boat : Main.VALID_BOATS) {
            int otherM = m - boat.getM();
            int otherC = c - boat.getC();

            childs.put(boat, new Node(otherM, otherC, otherMargin));
        }

    }

}
