package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 05.
 */
public abstract class Seeker {

    /**
     * Unit to measure the distance between two sequential points.
     */
    private static final byte UNIT = 1;

    /**
     * All points in the grid.
     */
    protected final List<Point> points = new ArrayList<>();

    /**
     * Map to get the point needed more quickly (with a unique key).
     */
    protected final Map<String, Point> map = new HashMap<>();

    /**
     * List with points to look.
     */
    private final Map<String, Point> targets = new HashMap<>();

    private int targetsSize = 0;

    /**
     * Visited points.
     */
    private final List<Point> visited = new ArrayList<>();

    /**
     * Object point. (final state)
     */
    private Point end;

    /**
     * Default construct.
     */
    public Seeker() {
        // empty
    }

    /**
     * Gets the given point copy on the grid.
     *
     * @param p Given point.
     * @return The point on the grid that represents the given point.
     */
    private Point getPoint(final Point p) {
        return map.get(p.getKey());
    }

    private void reset() {
        targets.clear();
        visited.clear();

        targetsSize = 0;
        end = null;

        for (Point p : points) {
            p.setVisited(false);
        }

        Collection<Point> values = map.values();
        for (Point p : values) {
            p.setVisited(false);
        }
    }

    /**
     * Look for a path between two points.
     *
     * @param start Start point.
     * @param end End point.
     */
    public void lookPath(final Point start, final Point end) {
        if (start == null || end == null) {
            System.out.println("Erro: dois valores não nulos são esperados");
            return;
        }

        if (!points.contains(start) || !points.contains(end)) {
            System.out.println("O grid não contém todos os pontos dados");
            return;
        }

        if (start.equals(end)) {
            System.out.println("O ponto inicial é igual ao final");
            return;
        }

        Point startP = getPoint(start);
        Point endP = getPoint(end);

        reset();

        this.end = endP;

        targets.put(startP.getKey(), startP);
        targetsSize = 1;

        long startTime = System.currentTimeMillis();
        look(startP);
        long endTime = System.currentTimeMillis();
        System.out.println("time: " + (endTime - startTime) + "ms");
    }

    private void look(final Point p) {
        if (p == null) {
            return;
        }
        if (targetsSize == 0) {
            System.out.println("path not found");
            return;
        }
        if (p.equals(end)) {
            System.out.println("path: " + visited);
            System.out.println("distance: " + (visited.size() * UNIT));
            return;
        }

        Point remove = targets.remove(p.getKey()); // remove the point from targets

        if (remove != null) {
            targetsSize--;
        }
        // only expand neighbors for point that was not expanded yet
        if (!p.isVisited()) {
            expandPoint(p); // expand point neighbors
        }

        p.setVisited(true);
        visited.add(p);

        Point bestPoint = getBestPoint(p);

        if (bestPoint == null) { // no valid neighbor to visit
            visited.remove(p);
            Point lastVisited = getLastVisited();

            if (lastVisited != null) {
                look(lastVisited);
            } else {
                System.out.println("path not found");
            }
        } else {
            look(bestPoint);
        }
    }

    private void expandPoint(final Point p) {
        Point top = new Point(p.getX(), p.getY() + UNIT, p.getZ());
        Point bottom = new Point(p.getX(), p.getY() - UNIT, p.getZ());
        Point front = new Point(p.getX(), p.getY(), p.getZ() + UNIT);
        Point back = new Point(p.getX(), p.getY(), p.getZ() - UNIT);
        Point left = new Point(p.getX() - UNIT, p.getY(), p.getZ());
        Point right = new Point(p.getX() + UNIT, p.getY(), p.getZ());

        List<Point> candidates = Arrays.asList(top, bottom, front, back, left, right);
        for (Point point : candidates) {
            Point neighbor = getPoint(point);

            if (neighbor != null && !neighbor.isBlocked() && !neighbor.isVisited()) {
                p.addNeighbor(neighbor);
                targets.put(neighbor.getKey(), neighbor);
                targetsSize++;
            }
        }
    }

    private Point getLastVisited() {
        if (!visited.isEmpty()) {
            return visited.get(visited.size() - 1);
        }
        return null;
    }

    /**
     * Gets the best point to be visited. The returned point is neighbor of given point.
     *
     * @param point Point with neighbors to look for.
     * @return The best point to visit by heuristic function.
     */
    private Point getBestPoint(final Point point) {
        List<Point> neighbors = point.getNeighbors();

        if (!neighbors.isEmpty()) {
            Point best = neighbors.get(0);

            int distance = visited.size() * UNIT; // g(n)
            double bestDistance = distance + end.distance(best); // f(n) = g(n) + h(n)

            if (neighbors.size() > 1) {
                for (int i = 1; i < neighbors.size(); i++) {
                    Point p = neighbors.get(i);

                    if (!p.isVisited() && !p.isBlocked()) {
                        double d = distance + end.distance(p);

                        if (d < bestDistance) {
                            bestDistance = d;
                            best = p;
                        }
                    }
                }
            }
            if (!best.isVisited() && !best.isBlocked()) {
                return best;
            }
        }
        // no valid neighbor
        return null;
    }

}
