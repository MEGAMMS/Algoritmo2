import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Form2.Grid;
import Problem1.Tree.Node;
import Utils.Filereader;

public class AppTest {
    @Test
    public void test1() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test1.txt");
        tree = Form1.Import("(" + in + ")");
        out = Form1.Export(tree);
        out = out.substring(1, out.length() - 1);
        assertEquals(out, in);
    }

    @Test
    public void test2() {
        String in;
        Node tree;
        Grid grid, grid2;

        in = Filereader.stringreader("src/Problem1/Form2/test1.txt");
        grid = new Grid(in);
        tree = Form2.Import(grid);
        grid2 = Form2.Export(tree);
        grid.print();
        if (grid2 != null) {
            grid2.print();
        }
        System.out.println(grid.equals(grid2));
    }
}
