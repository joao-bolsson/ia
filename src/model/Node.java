package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, Apr 09.
 */
public class Node extends State {

    private final Node parent;

    private final Map<Boat, Node> childs;

    private static final List<Node> OPENED = new ArrayList<>();

    /**
     * Creates a node.
     *
     * @param m Number of missionaries.
     * @param c Number of cannibals.
     * @param margin Margin where is the missionaries and cannibals.
     * @param parent Parent node.
     */
    public Node(final int m, final int c, final Margin margin, final Node parent) {
        super(m, c, margin);

        childs = new HashMap<>();
        this.parent = parent;
    }

    /**
     * Creates a node from a given state.
     *
     * @param state Given state to create the node.
     * @param parent Parent node.
     */
    public Node(final State state, final Node parent) {
        this(state.m, state.c, state.margin, parent);
    }

    private static int A = 0;

    private static final int B = 10;

    /**
     * Builds the node with children.
     */
    public void init() {
        if (OPENED.contains(this)) {
            System.out.println("ja foi aberto" + this);
            return;
        }
        OPENED.add(this);
        // checks if this node is valid (root case)
        if (!isValid()) {
            return;
        }

        for (Boat boat : Main.VALID_BOATS) {
            Node result = canApplyOperator(boat);
            if (result != null) {
                childs.put(boat, result);
            }
        }

        if (A < B) {
            A++;
        }

    }

    private Node canApplyOperator(final Boat boat) {
        Node currentNode = new Node(this, parent);

        currentNode.m -= boat.getM();
        currentNode.c -= boat.getC();

        // find how many missionaries and cannibals there is in the other side
        int otherM = Main.M_INIT - (currentNode.m + boat.getM());
        int otherC = Main.C_INIT - (currentNode.c + boat.getC());

        Margin otherMargin = margin.equals(Margin.Left) ? Margin.Right : Margin.Left;

        Node otherNode = new Node(otherM, otherC, otherMargin, this);
        otherNode.m += boat.getM();
        otherNode.c += boat.getC();

        if (currentNode.isValid() && otherNode.isValid() && boat.isValid() && !otherNode.equals(parent)) {
            return otherNode;
        }
        return null;
    }

    public void initChilds() {
        Iterator<Map.Entry<Boat, Node>> it = childs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Boat, Node> next = it.next();

            next.getValue().init();
            next.getValue().initChilds();
        }
    }

    /**
     * Print as a tree.
     *
     * @param level The level of node. 0 for root.
     * @param boat Boat that create the new state. (Can be null for root node for example).
     */
    public void print(final int level, final Boat boat) {
        String tabs = "";
        for (int i = 0; i < level; i++) {
            tabs += "  ";
        }
        if (boat != null) {
            tabs += "|" + "__(" + boat.toString() + ")___";
        }
        System.out.println(tabs + toString());
        Iterator<Map.Entry<Boat, Node>> it = childs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Boat, Node> next = it.next();

            next.getValue().print(level + 2, next.getKey());
        }
    }

}
