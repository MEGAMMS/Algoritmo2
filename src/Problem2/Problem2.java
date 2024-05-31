package Problem2;

import Problem2.Tree.Node;
import Utils.Filereader;

public class Problem2 {

    public static void main(String[] args) {
        String in = Filereader.stringreader("src/Problem2/test.txt");
        Node root = Import(in);
        String out=Export(root);
        System.out.println(out);
        System.out.println(BinaryConverter(root));
        Node Broot=_BinaryConverter(root);
        Node Kroot=_KaryTree(Broot);
        System.out.println(KaryTree(Broot));
    }
    // a function to split the input into an array of lines
    public static Node Import(String in) {
        String[] lines = in.split("\n");
        return _Import(lines, 0);
    }
    //a function to save the input as a tree of nodes
    private static Node _Import(String[] lines,int index){
        if (index >= lines.length) {
            return null;
        }
        //going over each line 
        String line = lines[index];
        int level = getLevel(line);
        String value = line.substring(0,level).trim();
        //creating the father node
        Node node = new Node(value);

        //assigning children nodes to an array
        String[] childNodes=line.substring(level+2,line.length()).trim().split(", ");
        
        //fathering children nodes properly 
        boolean isnull=true;
        for(int i=0;i<childNodes.length;i++){
            Node child;
            isnull=true;
            //go over all preceding lines to check if the node is leaf or not 
            for(int j=0;j<lines.length;j++){
                if(childNodes[i].equals(lines[j].substring(0,level).trim())){
                    child=_Import(lines, j);
                    // add it if succeeded in finding it
                    node.addChild(child);
                    isnull=false;
                    break;
                }
            }
            if(isnull){
                // this condition is for leaves
                child= new Node(childNodes[i],node);
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
    public static String Export(Node root){
        if(root==null){
            return null;
        }
        String out=root.toString()+"\n";
        for (Node child :root.getChildren() ) {
            if(!child.isLeaf())
            out=out+Export(child).trim()+"\n";
        }
        return out;
    } 
    // a function to turn a k-ary tree to a binary tree and return the result as a text
    public static String BinaryConverter(Node in){
        Node out =_BinaryConverter(in);
        return Export(out);
    }
    // a function to transform a k-ary tree into a binary tree
    public static Node _BinaryConverter(Node root){
        // creating a binary equivalent of the current k-ary node
        Node Bnode = new Node(root.getName());
        //checking if the node is a leaf or not bcz a leaf wouldn't have childern and thus no right node
        if(!root.isLeaf()){
            // filling up the missing parent parameter in some nodes due to mg3ms' magnificent node class
            root.fatheringChildren();
            //creating right node
            int rightward=root.getChildren().size()-1;
            Node RightBnode=_BinaryConverter(root.getChildren().get(rightward));
            Bnode.addChild(RightBnode);
        }
            // creating a left node if possible
            Node LeftBnode=null;
            if(root.getParent()!=null){
                int i=0;
                //loop is needed bcz children's structure is an arraylist rather than a hash map
                for (Node child : root.getParent().getChildren()) {
                    if(root.equals(child)){break;}
                    i++;
                }
                if(i>0){
                    LeftBnode=_BinaryConverter(root.getParent().getChildren().get(i-1));
                    LeftBnode.left=true;
                    Bnode.addChild(LeftBnode);
                }
            }
            
            
        
        return Bnode;
    }
    public static String KaryTree(Node in){
        Node out=_KaryTree(in);
        return Export(out);
    }

    public static Node _KaryTree(Node root){
        if(root==null){
            return null;
        }
        root.fatheringChildren();
        Node Knode=new Node(root.getName());
        
        System.out.println(root.getName());
        if(!root.isLeaf()){
            
            for (Node Child : root.getChildren()) {
                if(!Child.left){
                    Knode.addChild(_KaryTree(Child));
                }
                if(!Child.getChildren().isEmpty()){
                    for (int i = 0; i < Child.getChildren().size(); i++) {
                        if(Child.getChildren().get(i).left){
                            Knode.addChild(_KaryTree(Child.getChildren().get(i)));
                        }
                    }

                }
                Knode.fatheringChildren();
            }

        }
        
        
        return Knode;
    }

}
