package model;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class Boat {

    /**
     * Max number of passengers.
     */
    private static final int MAX = 2;

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
     * Returns if this operator is valid or not. The boat has a max capability that must be respected and the boat must
     * never be empty.
     *
     * @return If the number of missionaries is the boat is equals or greater than cannibals - true, else - false.
     */
    public boolean isValid() {
        if (m + c > 0 && m + c <= MAX) {
            if (m == 0) {
                return true;
            }
            return m >= c;
        }
        return false;
    }

    /**
     * @return The number of missionaries in the boat.
     */
    public int getM() {
        return m;
    }

    /**
     * @return The number of cannibals in the boat.
     */
    public int getC() {
        return c;
    }

    @Override
    public String toString() {
        return "<" + m + ", " + c + ">";
    }

}
