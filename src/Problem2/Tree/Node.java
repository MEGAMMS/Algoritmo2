package Problem2.Tree;

import java.util.ArrayList;

public class Node {
    String name;
    Node parent;
    ArrayList<Node> children;
    public boolean left = false;// indicates the left node in the binary tree

    public ArrayList<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    public Node(String name, Node parent, ArrayList<Node> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public Node(String name) {
        this.name = name;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void addParent(Node father) {
        this.parent = father;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        String s = this.name + " -> ";
        int i = 0;
        for (Node child : this.children) {
            s = s + child.name;
            if (i != children.size() - 1) {
                s += ", ";
            }
            i++;
        }
        return s;
    }

    public void fatheringChildren() {
        for (Node child : this.children) {
            child.addParent(this);
        }
    }

}