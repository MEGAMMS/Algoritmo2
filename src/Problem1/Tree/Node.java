package Problem1.Tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node left;
    public Node right;
    public Data data;

    public Node(Node left, Node right, Data data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node(Data data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return data.isLeaf();
    }

    public boolean valid() {
        if (this.isLeaf()) {
            return data.width > 2 && data.length > 2;
        }
        boolean ok = false;
        if (this.data.type == '-')
            ok = this.data.width == this.right.data.width
                    && this.data.width == this.left.data.width
                    && this.data.length == this.left.data.length + this.right.data.length;
        else
            ok = this.data.length == this.right.data.length
                    && this.data.length == this.left.data.length
                    && this.data.width == this.left.data.width + this.right.data.width;
        return ok && this.left.valid() && this.right.valid();
    }

    @Override
    public String toString() {
        return "(data: " + this.data+"" + ", left: " + this.left + ", right: " + this.right + ")";
    }


    // Method to print the tree structure
    public void printTree() {
        printTree("", true);
    }

    private void printTree(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + data);
        List<Node> children = new ArrayList<>();
        if (left != null) children.add(left);
        if (right != null) children.add(right);
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).printTree(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1).printTree(prefix + (isTail ? "    " : "│   "), true);
        }
    }

}
