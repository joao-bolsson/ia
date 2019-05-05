package model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 04.
 */
public class Grid extends Seeker {

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
                    Point p = new Point(x, y, z);
                    points.add(p);
                    map.put(p.toString(), p);

                    System.out.println(p);
                }
            }
        }

        int size = points.size();
        int blockeds = (int) (size * blocked); // number of blocked points.
        Integer[] indexes = new Integer[blockeds];

        Random random = new Random();
        for (int i = 0; i < indexes.length; i++) {
            List<Integer> list = Arrays.asList(indexes);

            int rand = random.nextInt(size - 1);
            while (list.contains(rand)) { // avoid repeated numbers
                rand = random.nextInt(size - 1);
            }

            indexes[i] = rand;
        }

        System.out.println("\nbloqueados:\n");
        // sets blocked points.
        for (int index : indexes) {
            System.out.println(points.get(index));
            points.get(index).setBlocked(true);
        }
    }

}
