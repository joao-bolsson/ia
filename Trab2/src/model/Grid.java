package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 04.
 */
public class Grid {

    private final List<Point> points = new ArrayList<>();

    /**
     * Creates a grid.
     *
     * @param dimension Grid dimension. Ex: '3' will generate a grid 3x3x3.
     * @param blocked Percent of blocked points on the grid.
     */
    public Grid(final int dimension, final float blocked) {
        // initialize the points
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                for (int z = 0; z < dimension; z++) {
                    points.add(new Point(x, y, z));
                }
            }
        }

        int size = points.size();
        int blockeds = (int) (size * blocked); // number of blocked points.
        int[] indexes = new int[blockeds];

        Random random = new Random();
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = random.nextInt(size - 1); // random number
        }

        // sets blocked points.
        for (int index : indexes) {
            points.get(index).setBlocked(true);
        }

        initNeighbors();
    }

    private void initNeighbors() {
        // TODO
    }

    /**
     * Gets the given point copy on the grid.
     *
     * @param p Given point.
     * @return The point on the grid that represents the given point.
     */
    private Point getPoint(final Point p) {
        for (Point point : points) {
            if (point.equals(p)) {
                return point;
            }
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
        if (!points.contains(start) || !points.contains(end)) {
            System.out.println("O grid não contém todos os pontos dados");
            return;
        }

        Point startP = getPoint(start);
        Point endP = getPoint(end);
        // TODO: A*
    }

}
