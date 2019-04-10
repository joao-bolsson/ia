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

    private final Vertice parent;

    private final Map<Boat, Vertice> neighbors = new HashMap<>();

    public Vertice(final State state, final Vertice parent) {
        super(state.m, state.c, state.margin);
        this.parent = null;
    }

    public Vertice(final int m, final int c, final Margin margin, final Vertice parent) {
        super(m, c, margin);

        this.parent = parent;
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

    public Vertice getParent() {
        return parent;
    }

    public Vertice canApplyOperator(final Boat boat) {
        Vertice currentNode = new Vertice(this, parent);

        currentNode.m -= boat.getM();
        currentNode.c -= boat.getC();

        // find how many missionaries and cannibals there is in the other side
        int otherM = Main.M_INIT - (currentNode.m + boat.getM());
        int otherC = Main.C_INIT - (currentNode.c + boat.getC());

        Margin otherMargin = margin.equals(Margin.Left) ? Margin.Right : Margin.Left;

        Vertice otherNode = new Vertice(otherM, otherC, otherMargin, this);
        otherNode.m += boat.getM();
        otherNode.c += boat.getC();

        if (currentNode.isValid() && otherNode.isValid() && boat.isValid() && !otherNode.equals(parent)) {
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
        hash = 29 * hash + Objects.hashCode(this.parent);
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

        boolean parentEquals = (vertice.parent == null && parent == null) || (parent != null && parent.equals(vertice))
                || (vertice.parent != null && vertice.parent.equals(parent));

        return !(vertice.c != c || vertice.m != m || !vertice.margin.equals(margin) || !parentEquals);
    }

}
