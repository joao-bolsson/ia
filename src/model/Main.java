package model;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class Main {

    private static final byte M_INIT = 3, C_INIT = 3;
    private static final State.Margin INITIAL_MARGIN = State.Margin.Left;

    private static final byte M_FINAL = 3, C_FINAL = 3;
    private static final State.Margin FINAL_MARGIN = State.Margin.Right;

    private final State initialState, finalState;

    private Main() {
        // initial state
        initialState = new State(M_INIT, C_INIT, INITIAL_MARGIN);
        // objective
        finalState = new State(M_FINAL, C_FINAL, FINAL_MARGIN);
    }

    private void run() {
        // do nothing
    }

    /**
     * Main function.
     *
     * @param args Command line arguments (ignore).
     */
    public static void main(final String[] args) {
        Main main = new Main();
        main.run();
    }

}
