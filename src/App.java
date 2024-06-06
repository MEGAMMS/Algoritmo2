import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Tree.Node;
import Utils.Filereader;

public class App {
    public static void main(String[] args) throws Exception {
        String in;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test3.txt");
        tree = Form1.Import(in);
        tree.printTree();
        Form2.Export(tree).print();
        tree.rotate();
        System.err.println(tree.valid());
        tree.printTree();
        Form2.Export(tree).print();
    }
}
