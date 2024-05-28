package Problem2.Tree;

import java.util.ArrayList;

public class Node {
    String name;
    Node parent;
    ArrayList<Node> children;

    public ArrayList<Node> getChildren() {
        return children;
    }
    public String getName() {
        return name;
    }

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
    public Node(String name) {
        this.name = name;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }
    
    @Override
    public String toString() {
        String s=this.name+" -> ";
        int i=0;
        for(Node child: this.children){
            s=s+child.name;
            if(i!=children.size()-1){
                s+=", ";
            }
            i++;
        }
        return s;
    }

}