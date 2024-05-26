package Problem2.Tree;

import java.util.ArrayList;

public class Node {
    String name;
    Node parent;
    ArrayList<Node> children;

    public boolean isLeaf() {
        return children == null;
    }

    public Node(String name, Node parent, ArrayList<Node> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        this.children = null;
    }
}