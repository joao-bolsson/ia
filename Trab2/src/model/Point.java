package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 04.
 */
public class Point {

    private final int x, y, z;

    private boolean blocked = false;

    private boolean visited = false;

    private final List<Point> neighbors = new ArrayList<>();

    /**
     * Creates a 3D point.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public Point(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return The x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * @return The z coordinate.
     */
    public int getZ() {
        return z;
    }

    /**
     * @return Returns if this point is blocked or not.
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Sets if this point is blocked or not.
     *
     * @param blocked Flag to set.
     */
    public void setBlocked(final boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * @return Returns if this point was visited or not.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets if this point was visited or not.
     *
     * @param visited Flag to set.
     */
    public void setVisited(final boolean visited) {
        this.visited = visited;
    }

    /**
     * Adds a point neighbor.
     *
     * @param p Neighbor point.
     */
    public void addNeighbor(final Point p) {
        if (!neighbors.contains(p)) {
            neighbors.add(p);
        }
    }

    /**
     * Returns the distance between this point and a given point.
     *
     * @param p Given point.
     * @return Distance between points.
     */
    public double distance(final Point p) {
        if (p != null) {
            return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2) + Math.pow(z - p.z, 2));
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.x;
        hash = 11 * hash + this.y;
        hash = 11 * hash + this.z;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Point)) {
            return false;
        }

        final Point other = (Point) obj;

        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    @Override
    public String toString() {
        return "P(" + x + ", " + y + ", " + z + ") " + (blocked ? "blocked" : "");
    }

}
