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

    public boolean isLeaf() {
        return data.isLeaf();
    }

    public boolean valid() {
        if (this.isLeaf()) {
            return data.width > 2 && data.length > 2;
        }
        if (this.data.type == '-') {
            return this.data.width == this.right.data.width
                    && this.data.width == this.left.data.width
                    && this.data.length == this.left.data.length + this.right.data.length - 1;
        }
        return this.data.length == this.right.data.length
                && this.data.length == this.left.data.length
                && this.data.width == this.left.data.width + this.right.data.width - 1;
    }

}
