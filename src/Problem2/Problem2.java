package Problem2;

import java.util.ArrayList;
import java.util.Arrays;

import Problem2.Tree.Node;
import Utils.Filereader;

public class Problem2 {

    public static void main(String[] args) {
        String in = Filereader.stringreader("src/Problem2/test5.txt");
        Node root = Import(in);
        String out = Export(root);
        System.out.println(out);
        System.out.println(BinaryConverter(root));

        Node Binaryroot = _BinaryConverter(root);
        // Node Kroot=_KaryTree(Binaryroot);
        System.out.println(KaryTreeConverter(Binaryroot));
    }
    // a function to split the input into an array of lines then save it as a tree
    // of nodes

    public static Node Import(String in) {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(in.split("\n")));
        return _Import(lines, 0);
    }

    // a function to save the input as a tree of nodes
    private static Node _Import(ArrayList<String> lines, int index) {
        // index refers to line we're at in the array of lines
        System.err.println(lines);
        if (index >= lines.size()) {
            return null;
        }
        // going over each line
        String line = lines.get(index);
        int level = getLevel(line);

        String value = line.substring(0, level).trim();
        // creating the parent node
        Node node = new Node(value);

        // assigning children nodes to an array
        if (level == 1) {
            return node;
        }
        String[] childNodes = line.substring(level + 2, line.length()).trim().split(", ");

        // fathering children nodes properly
        boolean isNull = true;
        for (int i = 0; i < childNodes.length; i++) {
            Node child;// declaring a child node
            isNull = true;// this variable indicates the state of the node whether it's a leaf or not

            // go over all preceding lines to check if the node is leaf or not
            for (int j = 0; j < lines.size(); j++) {
                if (childNodes[i].equals(lines.get(j).substring(0, level).trim())) {
                    child = _Import(lines, j);

                    // add it if succeeded in finding it
                    node.addChild(child);
                    isNull = false;
                    break;
                }
            }
            if (isNull) {
                // this condition is for leaves
                child = new Node(childNodes[i], node);
                node.addChild(child);
            }

        }

        return node;
    }

    // a function to find the index of "-" so we wouldn't count it manually
    public static int getLevel(String line) {
        int level = 0;
        while (level < line.length() && line.charAt(level) != '-') {
            level++;
        }
        return level;
    }

    // a function to output the tree
    public static String Export(Node root) {
        if (root == null) {
            return null;
        }
        String out = root.toString() + "\n";
        for (Node child : root.getChildren()) {
            if (!child.isLeaf())
                out = out + Export(child).trim() + "\n";
        }
        return out;
    }

    // a function to turn a k-ary tree to a binary tree and return the result as a
    // text
    public static String BinaryConverter(Node in) {
        Node out = _BinaryConverter(in);
        return Export(out);
    }

    // a function to transform a k-ary tree into a binary tree
    public static Node _BinaryConverter(Node root) {
        // creating a binary equivalent of the current k-ary node
        Node Bnode = new Node(root.getName());
        // checking if the node is a leaf or not bcz a leaf wouldn't have childern and
        // thus no right node
        if (!root.isLeaf()) {
            // filling up the missing parent parameter in some nodes due to mg3ms'
            // magnificent node class
            root.fatheringChildren();
            // creating right node
            int rightward = root.getChildren().size() - 1;
            Node RightBnode = _BinaryConverter(root.getChildren().get(rightward));
            Bnode.addChild(RightBnode);
        }
        // creating a left node if possible
        Node LeftBnode = null;
        if (root.getParent() != null) {
            int i = 0;
            // loop is needed bcz children's structure is an arraylist rather than a hash
            // map
            for (Node child : root.getParent().getChildren()) {
                if (root.equals(child)) {
                    break;
                }
                i++;
            }
            if (i > 0) {
                LeftBnode = _BinaryConverter(root.getParent().getChildren().get(i - 1));
                LeftBnode.left = true;
                Bnode.addChild(LeftBnode);
            }
        }

        return Bnode;
    }

    // this function returns the k-ary equivalent of the binary tree input as a
    // string
    public static String KaryTreeConverter(Node in) {
        Node out = _KaryTreeConverter(in);
        return Export(out);
    }

    // transforms a binary tree to a k-ary tree
    public static Node _KaryTreeConverter(Node root) {
        if (root == null) {
            return null;
        }
        root.fatheringChildren();// assign the parent to each of his children
        Node Knode = new Node(root.getName());

        if (!root.isLeaf()) {

            for (Node Child : root.getChildren()) {
                if (!Child.left) {
                    // start by finding all the left children then proceed to add them
                    if (!Child.getChildren().isEmpty()) {
                        ArrayList<Node> leftnodes = new ArrayList<>();
                        Goleft(Child, leftnodes);
                        for (int i = 0; i < leftnodes.size(); i++) {
                            Knode.addChild(_KaryTreeConverter(leftnodes.get(leftnodes.size() - 1 - i)));

                        }
                    }
                    // added at the end to aline the correspondence of element to the original tree
                    Knode.addChild(_KaryTreeConverter(Child));
                }
                Knode.fatheringChildren();

            }

        }

        return Knode;
    }

    // a functiion to go over left nodes
    public static void Goleft(Node root, ArrayList<Node> leftnodes) {
        if (root == null || root.isLeaf()) {
            return;
        }
        // keep going left until there is no left nodes left
        for (Node node : root.getChildren()) {
            if (node.left) {
                leftnodes.add(node);
                Goleft(node, leftnodes);

            }

        }
    }
}