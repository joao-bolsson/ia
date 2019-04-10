package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 10.
 */
public class Vertice extends State {

    private boolean visited = false;

    private final Map<Boat, Vertice> neighbors = new HashMap<>();

    public Vertice(final State state) {
        super(state.m, state.c, state.margin);
    }

    public Vertice(final int m, final int c, final Margin margin) {
        super(m, c, margin);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public Map<Boat, Vertice> getNeighbors() {
        if (neighbors.isEmpty()) {
            for (Boat boat : Main.VALID_BOATS) {
                Vertice child = canApplyOperator(boat);
                if (child != null) {
                    neighbors.put(boat, child);
                }
            }
        }

        return neighbors;
    }

    public Vertice canApplyOperator(final Boat boat) {
        Vertice currentNode = new Vertice(this);

        currentNode.m -= boat.getM();
        currentNode.c -= boat.getC();

        // find how many missionaries and cannibals there is in the other side
        int otherM = Main.M_INIT - (currentNode.m + boat.getM());
        int otherC = Main.C_INIT - (currentNode.c + boat.getC());

        Margin otherMargin = margin.equals(Margin.Left) ? Margin.Right : Margin.Left;

        Vertice otherNode = new Vertice(otherM, otherC, otherMargin);
        otherNode.m += boat.getM();
        otherNode.c += boat.getC();

        if (currentNode.isValid() && otherNode.isValid() && boat.isValid()) {
            return otherNode;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 77;
        hash = 29 * hash + Objects.hashCode(this.c);
        hash = 29 * hash + Objects.hashCode(this.m);
        hash = 29 * hash + Objects.hashCode(this.margin);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Vertice)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Vertice vertice = (Vertice) obj;

        return !(vertice.c != c || vertice.m != m || !vertice.margin.equals(margin));
    }

}
