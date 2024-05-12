package Problem1.Tree;

public class Node {
    int key;
    Node left, right;
    int length, width;

    public Node(int item, Node left, Node right) {
        key = item;
        this.left = left;
        this.right = right;
    }

    public Node(int item) {
        key = item;
    }

    public Node(int item, int length, int width) {
        key = item;
        this.length = length;
        this.width = width;
    }

    public Node(int item, int length, int width, Node left, Node right) {
        key = item;
        this.length = length;
        this.width = width;
        this.left = left;
        this.right = right;
    }

}
