package Problem1.Tree;

public class Node {
    Node left, right;
    Data data;
    public Node(Node left, Node right, Data data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node(Data data){
        this.data = data;
    }

}
