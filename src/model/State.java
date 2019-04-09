package model;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class State {

    /**
     * ENUM for margins (left and right only).
     */
    public enum Margin {

        Left("Left"), Right("Right");

        private final String code;

        Margin(final String code) {
            this.code = code;
        }

        /**
         * @return The code for this Margin.
         */
        public String getCode() {
            return code;
        }

    }

    private final int m, c;
    private final Margin margin;

    /**
     * Creates a state. Default construct.
     *
     * @param m Number of missionaries.
     * @param c Number of cannibals.
     * @param margin The margin where is the missionaries and cannibals.
     */
    public State(final int m, final int c, final Margin margin) {
        this.m = m;
        this.c = c;
        this.margin = margin;
    }

    /**
     * Returns a flag to indicate if this state is valid or not: The number of missionaries must never be lower than
     * cannibals.
     *
     * @return If number of missionaries is equals or greater than cannibals - true, else - false.
     */
    public boolean isValid() {
        return m >= c;
    }

    /**
     * @return The margin where is the missionaries and cannibals.
     */
    public Margin getMargin() {
        return margin;
    }

}
