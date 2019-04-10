package model;

import java.util.Objects;

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
        if (m < 0 || c < 0) {
            return false;
        }
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

    @Override
    public int hashCode() {
        int hash = 79;
        hash = 18 * hash + Objects.hashCode(this.c);
        hash = 86 * hash + Objects.hashCode(this.m);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Boat)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Boat boat = (Boat) obj;

        return boat.c == c && boat.m == m;
    }

}
