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
    public boolean isLeaf(){
        return data.isLeaf();
    }
    public boolean valid(){
        if(this.isLeaf()){
            return data.width > 2 && data.length > 2;
        }
        return false;
    }


}
