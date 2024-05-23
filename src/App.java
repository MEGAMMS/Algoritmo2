import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Form2.Grid;
import Problem1.Tree.Node;
import Utils.Filereader;

public class App {
    public static void main(String[] args) throws Exception {
        String s, out;
        Node tree;
        Grid grid, grid2;
        // Test 1
        s = Filereader.stringreader("src/Problem1/Form1/test1.txt");
        tree = Form1.Import("(" + s + ")");
        out = Form1.Export(tree);
        out = out.substring(1, out.length() - 1);
        System.out.println(out.equals(s));

        // Test 2
        s = Filereader.stringreader("src/Problem1/Form2/test1.txt");
        grid = new Grid(s);
        tree = Form2.Import(grid);
        grid2 = Form2.Export(tree);
        grid.print();
        grid2.print();
        System.out.println(grid.equals(grid2));
    }
}
