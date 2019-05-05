package model;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final List<Point> targets = new ArrayList<>();

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
     * Avoid start or end point blocked.
     *
     * @param start Start point.
     * @param end End point.
     */
    protected abstract void resolveBlocked(final Point start, final Point end);

    /**
     * Gets the given point copy on the grid.
     *
     * @param p Given point.
     * @return The point on the grid that represents the given point.
     */
    private Point getPoint(final Point p) {
        if (points.contains(p)) {
            return map.get(p.getKey());
        }
        return null;
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

        Point startP = getPoint(start);
        Point endP = getPoint(end);

        if (startP.equals(endP)) {
            System.out.println("O ponto inicial é igual ao final");
            return;
        }

        resolveBlocked(start, end);

        this.end = endP;

        targets.clear();
        visited.clear();

        targets.add(startP);

        long startTime = System.currentTimeMillis();
        look(startP);
        long endTime = System.currentTimeMillis();
        System.out.println("time: " + (endTime - startTime) + "ms");
    }

    private void look(final Point p) {
        if (p == null) {
            System.out.println("path not found");
            return;
        }
        if (end.equals(p)) {
            System.out.println("path: " + visited);
            System.out.println("distance: " + (visited.size() * UNIT));
            return;
        }

        targets.remove(p); // remove the point from targets

        p.setVisited(true);
        visited.add(p);

        // gets the neighbors of the point
        Point top = new Point(p.getX(), p.getY() + UNIT, p.getZ());
        Point bottom = new Point(p.getX(), p.getY() - UNIT, p.getZ());
        Point front = new Point(p.getX(), p.getY(), p.getZ() + UNIT);
        Point back = new Point(p.getX(), p.getY(), p.getZ() - UNIT);
        Point left = new Point(p.getX() - UNIT, p.getY(), p.getZ());
        Point right = new Point(p.getX() + UNIT, p.getY(), p.getZ());

        List<Point> candidates = Arrays.asList(top, bottom, front, back, left, right);
        for (Point point : candidates) {
            Point neighbor = getPoint(point);

            if (neighbor != null && !neighbor.isBlocked() && !neighbor.isVisited() && !targets.contains(neighbor)) {
                p.addNeighbor(neighbor);
                targets.add(neighbor);
            }
        }

        Point bestPoint = getBestPoint(p);

        look(bestPoint);
    }

    /**
     * Gets the best point to be visited.
     *
     * @return The best point to visit by heuristic function.
     */
    private Point getBestPoint(final Point p) {
        if (!targets.isEmpty()) {
            Point best = targets.get(0);

            int distance = visited.size() * UNIT; // g(n)
            double bestDistance = distance + end.distance(best); // f(n) = g(n) + h(n)

            if (targets.size() > 1) {
                for (int i = 1; i < targets.size(); i++) {
                    Point point = targets.get(i);
                    double d = distance + end.distance(point);

                    if (d < bestDistance) {
                        bestDistance = d;
                        best = point;
                    }
                }
            }
            return best;
        }
        return null;
    }

}
