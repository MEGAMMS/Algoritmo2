package Problem1.Tree;

class Data {
    String name;
    char type;
    int length;
    int width;
}


public class Node {
    Node left, right;
    Data data;
    public Node(Node left, Node right, Data data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node(Node left, Node right){
        this.left = left;
        this.right = right;
    }

}
