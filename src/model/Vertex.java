package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 10.
 */
public class Vertex extends State {

    private boolean visited = false;

    private final Map<Boat, Vertex> neighbors = new HashMap<>();

    public Vertex(final State state) {
        super(state.m, state.c, state.margin);
    }

    public Vertex(final int m, final int c, final Margin margin) {
        super(m, c, margin);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public Map<Boat, Vertex> getNeighbors() {
        if (neighbors.isEmpty()) {
            for (Boat boat : Main.VALID_BOATS) {
                Vertex child = canApplyOperator(boat);
                if (child != null) {
                    neighbors.put(boat, child);
                }
            }
        }

        return neighbors;
    }

    public Vertex canApplyOperator(final Boat boat) {
        Vertex currentNode = new Vertex(this);

        currentNode.m -= boat.getM();
        currentNode.c -= boat.getC();

        // find how many missionaries and cannibals there is in the other side
        int otherM = Main.M_INIT - (currentNode.m + boat.getM());
        int otherC = Main.C_INIT - (currentNode.c + boat.getC());

        Margin otherMargin = margin.equals(Margin.Left) ? Margin.Right : Margin.Left;

        Vertex otherNode = new Vertex(otherM, otherC, otherMargin);
        otherNode.m += boat.getM();
        otherNode.c += boat.getC();

        if (currentNode.isValid() && otherNode.isValid() && boat.isValid()) {
            return otherNode;
        }
        return null;
    }

}
