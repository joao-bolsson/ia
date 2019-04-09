package model;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class Boat {

    private final int m, c;

    /**
     * Creates a boat (operator) to transport missionaries and cannibals.
     *
     * @param m Number of missionaries in the boat.
     * @param c Number of cannibals in the boat.
     */
    public Boat(final int m, final int c) {
        this.m = m;
        this.c = c;
    }

    /**
     * Returns if this operator is valid or not.
     *
     * @return If the number of missionaries is the boat is equals or greater than cannibals - true, else - false.
     */
    public boolean isValid() {
        return m >= c;
    }

}
