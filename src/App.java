import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Form2.Grid;
import Problem1.Tree.Node;
import Utils.Filereader;

public class App {
    public static void main(String[] args) throws Exception {
        // String in,out;
        // Node tree;
        // Grid grid, grid2;

        // in = Filereader.stringreader("src/Problem1/Form2/test1.txt");
        // grid = new Grid(in);
        // tree = Form2.Import(grid);
        // out = Form1.Export(tree);
        // out = out.substring(1, out.length() - 1);
        // System.out.println(out);
        // grid2 = Form2.Export(tree);

        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test1.txt");
        tree = Form1.Import(in);
        out = Form1.Export(tree);
        System.out.println(out);

        Grid grid;
        grid = Form2.Export(tree);
        grid.print();
    }
}
