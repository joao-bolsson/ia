package model;

import java.util.Objects;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 10.
 */
public class Edge {

    private final Boat boat;
    private final Vertex origin;
    private final Vertex dest;
    private boolean visited = false;

    public Edge(final Boat value, final Vertex origin, final Vertex dest) {
        this.boat = value;
        this.origin = origin;
        this.dest = dest;
    }

    public boolean isVisited() {
        return visited;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDest() {
        return dest;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setVisited(final boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return origin + "__" + boat + "__" + dest;
    }

    @Override
    public int hashCode() {
        int hash = 12;
        hash = 78 * hash + Objects.hashCode(this.origin);
        hash = 78 * hash + Objects.hashCode(this.dest);
        hash = 78 * hash + Objects.hashCode(this.boat);
        hash = 78 * hash + Objects.hashCode(this.visited);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Edge edge = (Edge) obj;

        return edge.dest.equals(dest) && edge.origin.equals(origin) && edge.boat.equals(boat)
                && edge.visited == visited;
    }

}
