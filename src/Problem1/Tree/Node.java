package Problem1.Tree;

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

}
