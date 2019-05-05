package main;

import model.Grid;
import model.Point;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 04.
 */
public class Main {

    private static final byte DIMENSION = 3;
    private static final float BLOCKED = 0.1f;

    /**
     * Main function.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        Grid grid = new Grid(DIMENSION, BLOCKED);
        grid.lookPath(new Point(0, 0, 0), new Point(DIMENSION, DIMENSION, DIMENSION));
    }

}
