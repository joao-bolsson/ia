package main;

import model.Grid;
import model.Point;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 04.
 */
public class Main {

    private static final byte DIMENSION = 5;
    private static final float BLOCKED = 0f;

    /**
     * Main function.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        Grid grid = new Grid(DIMENSION, BLOCKED);

        Point start = null, end = null;
        for (int i = 0; i < 50; i++) {
            while (start == null) {
                start = grid.getRandomPoint();
            }
            while (end == null || start.equals(end)) {
                end = grid.getRandomPoint();
            }
            System.out.println("================================================================================");
            System.out.println("start: " + start + " end: " + end);
            grid.lookPath(start, end);
            start = end = null;
        }

    }

}
