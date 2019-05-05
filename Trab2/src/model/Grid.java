package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 04.
 */
public class Grid extends Seeker {

    private static final byte COORDINATES = 3;

    private final List<Integer> indexesBlocked = new ArrayList<>();

    /**
     * Creates a grid.
     *
     * @param dimension Grid dimension. Ex: '3' will generate a grid 3x3x3.
     * @param blocked Percent of blocked points on the grid.
     */
    public Grid(final int dimension, final float blocked) {
        int size = (int) Math.pow(dimension, COORDINATES);
        int blockeds = (int) (size * blocked); // number of blocked points.

        Random random = new Random();
        for (int i = 0; i < blockeds; i++) {
            int rand = random.nextInt(size);
            while (indexesBlocked.contains(rand)) { // avoid repeated numbers
                rand = random.nextInt(size);
            }

            indexesBlocked.add(rand);
        }

        int i = 0;

        // initialize the points
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                for (int z = 0; z < dimension; z++) {
                    Point p = new Point(x, y, z);
                    if (indexesBlocked.contains(i)) {
                        p.setBlocked(true);
                    }
                    points.add(p);
                    map.put(p.getKey(), p);
                    i++;
                }
            }
        }
    }

    @Override
    protected void resolveBlocked(final Point start, final Point end) {
        if (start.isBlocked()) {
            removeBlocked(start);
        }

        if (end.isBlocked()) {
            removeBlocked(end);
        }
    }

    private void removeBlocked(final Point p) {
        int size = points.size();
        Random random = new Random();
        int rand = random.nextInt(size);
        while (indexesBlocked.contains(rand)) { // avoid repeated numbers
            rand = random.nextInt(size);
        }

        indexesBlocked.add(rand);

        points.get(rand).setBlocked(true);
        p.setBlocked(false);

        int indexOf = points.indexOf(p);
        if (indexOf >= 0) {
            indexesBlocked.remove(new Integer(indexOf));
        }
    }

    /**
     * Gets a random point from the grid.
     *
     * @return A random point.
     */
    public Point getRandomPoint() {
        if (!points.isEmpty()) {
            int size = points.size();
            Random random = new Random();
            int rand = random.nextInt(size);

            return points.get(rand);
        }
        return null;
    }

}
