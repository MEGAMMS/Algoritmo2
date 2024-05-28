package Problem2;

import Problem2.Tree.Node;
import Problem2.Tree.Tree;
import Utils.Filereader;

public class Problem2 {

    public static void main(String[] args) {
        String in = Filereader.stringreader("src/Problem2/test3.txt");
        Node root = Import(in);
        String out=Export(root);
        System.out.println(out);
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
        System.out.println(value);
        //creating the father node
        Node node = new Node(value);

        //assigning children nodes to an array
        String[] childNodes=line.substring(level+2,line.length()).trim().split(", ");
        
        //fathering children nodes properly 
        int localIndex=index+1;
        boolean isnull=true;
        for(int i=0;i<childNodes.length;i++){
            Node child;
            System.out.println(childNodes[i]);
            for(int j=0;j<lines.length;j++){
                if(childNodes[i].equals(lines[j].substring(0,level).trim())){
                    child=_Import(lines, j);
                    //localIndex++;
                    node.addChild(child);
                    isnull=false;
                    break;
                }
            }
            if(isnull){
                child= new Node(childNodes[i],node);
                node.addChild(child);
            }

        }

        return node;
    }

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
}
