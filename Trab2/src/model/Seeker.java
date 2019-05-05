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
public class Seeker {

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
     * Gets the given point copy on the grid.
     *
     * @param p Given point.
     * @return The point on the grid that represents the given point.
     */
    private Point getPoint(final Point p) {
        if (points.contains(p)) {
            return map.get(p.toString());
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
            System.out.println("Erro: esperados dois valores não nulos");
            return;
        }

        if (!points.contains(start) || !points.contains(end)) {
            System.out.println("O grid não contém todos os pontos dados");
            return;
        }

        Point startP = getPoint(start);
        Point endP = getPoint(end);

        if (startP.isBlocked() || endP.isBlocked()) {
            System.out.println("Os pontos de inicio e fim não estão todos livres (estão bloqueados)");
            return;
        }

        this.end = endP;

        targets.clear();
        visited.clear();

        targets.add(startP);

        look(startP);
    }

    private void look(final Point p) {
        if (p == null) {
            System.out.println("path not found");
            return;
        }
        if (end.equals(p)) {
            System.out.println("path: " + visited);
            return;
        }

        targets.remove(p); // remove the point from targets

        p.setVisited(true);
        visited.add(p);

        // gets the neighbors of the point
        Point top = new Point(p.getX(), p.getY() + 1, p.getZ());
        Point bottom = new Point(p.getX(), p.getY() - 1, p.getZ());
        Point front = new Point(p.getX(), p.getY(), p.getZ() + 1);
        Point back = new Point(p.getX(), p.getY(), p.getZ() - 1);
        Point left = new Point(p.getX() - 1, p.getY(), p.getZ());
        Point right = new Point(p.getX() + 1, p.getY(), p.getZ());

        List<Point> candidates = Arrays.asList(top, bottom, front, back, left, right);
        for (Point point : candidates) {
            Point neighbor = getPoint(point);

            if (neighbor != null && !neighbor.isBlocked() && !neighbor.isVisited() && !targets.contains(neighbor)) {
                targets.add(neighbor);
            }
        }

        Point bestPoint = getBestPoint();

        look(bestPoint);
    }

    /**
     * Gets the best point to be visited.
     *
     * @return The best point to visit by heuristic function.
     */
    private Point getBestPoint() {
        if (!targets.isEmpty()) {
            Point best = targets.get(0);

            int distance = visited.size() * UNIT; // g(n)
            double bestDistance = distance + end.distance(best); // f(n) = g(n) + h(n)

            if (targets.size() > 1) {
                for (int i = 1; i < targets.size(); i++) {
                    double d = distance + end.distance(targets.get(i));

                    if (d < bestDistance) {
                        bestDistance = d;
                        best = targets.get(i);
                    }
                }
            }
            return best;
        }
        return null;
    }

}
