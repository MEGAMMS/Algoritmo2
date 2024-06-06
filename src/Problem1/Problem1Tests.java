package Problem1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Form2.Grid;
import Problem1.Tree.Data;
import Problem1.Tree.Node;
import Utils.Filereader;

import Problem1.Form2.Line;

public class Problem1Tests {
    @Test
    public void test1() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test1.txt");
        tree = Form1.Import(in);
        out = Form1.Export(tree);
        assertEquals(tree.valid(), true);
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
        assertEquals(grid, grid2);
        assertEquals(tree.valid(), true);
    }

    @Test
    public void test3() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test2.txt");
        tree = Form1.Import(in);
        out = Form1.Export(tree);
        assertEquals(out, in);
        assertEquals(tree.valid(), true);
    }

    @Test
    public void test4() {
        String in;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test2.txt");
        tree = Form1.Import(in);
        System.out.println(tree.data);
        System.out.println(tree.right.data);
        System.out.println(tree.left.data);
        assertEquals(tree.data.type, '|');
        assertEquals(0, tree.left.data.length - tree.data.length);
        assertEquals(tree.data.length, tree.right.data.length);
        assertEquals(tree.data.width, tree.right.data.width + tree.left.data.width);
        assertEquals(tree.valid(), true);
    }

    @Test
    public void test5() {
        String in = Filereader.stringreader("src/Problem1/Form2/test3.txt");
        Node n = new Node(new Data('A', 5, 4));
        Grid grid = n.data.gridBuilder();
        Grid gridExp = new Grid(in);
        gridExp.print();
        grid.print();
        assertEquals(gridExp, grid);
    }

    @Test
    public void test6() {
        String ex = Filereader.stringreader("src/Problem1/Form2/test4.txt");
        Grid gridEx = new Grid(ex);
        Node a = new Node(new Data('A', 5, 4));
        Node b = new Node(new Data('B', 4, 4));
        Grid grid = Form2.Merger(a.data.gridBuilder(), b.data.gridBuilder(), '|');
        gridEx.print();
        grid.print();
        assertEquals(gridEx, grid);
    }

    @Test
    public void test7() {
        String ex = Filereader.stringreader("src/Problem1/Form2/test5.txt");
        Grid gridEx = new Grid(ex);
        Node a = new Node(new Data('A', 5, 4));
        Node b = new Node(new Data('B', 5, 3));
        Grid grid = Form2.Merger(a.data.gridBuilder(), b.data.gridBuilder(), '-');
        gridEx.print();
        grid.print();
        assertEquals(gridEx, grid);
    }

    @Test
    public void test8() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test3.txt");
        tree = Form1.Import(in);
        out = Form1.Export(tree);
        System.err.println(out);
        assertEquals(in, out);
        Form2.Export(tree);
    }

    @Test
    public void test9() {
        String s = Filereader.stringreader("src/Problem1/Form2/test5.txt");
        Grid grid = new Grid(s);
        grid.print();
        Integer acutal = Form2.lineIdxToBreak(grid, Line.ROW);
        assertEquals(Integer.valueOf(3), acutal);
    }

    @Test
    public void test10() {
        String s = Filereader.stringreader("src/Problem1/Form2/test4.txt");
        Grid grid = new Grid(s);
        grid.print();
        Integer acutal = Form2.lineIdxToBreak(grid, Line.COL);
        assertEquals(Integer.valueOf(4), acutal);
    }

    @Test
    public void test11() {
        String s = Filereader.stringreader("src/Problem1/Form2/test3.txt");
        Grid grid = new Grid(s);
        grid.print();
        Integer acutal = Form2.lineIdxToBreak(grid, Line.COL);
        Integer acutal2 = Form2.lineIdxToBreak(grid, Line.ROW);
        assertEquals(null, acutal);
        assertEquals(null, acutal2);
    }

    @Test
    public void test12() {
        String s = Filereader.stringreader("src/Problem1/Form2/test3.txt");
        Grid grid = new Grid(s);
        Data data = new Data(grid);
        assertEquals(new Data('A', 5, 4).toString(), data.toString());
    }

    @Test
    public void test14() {
        String s = Filereader.stringreader("src/Problem1/Form2/test4.txt");
        String sa = Filereader.stringreader("src/Problem1/Form2/test3.txt");
        Grid grid = new Grid(s);
        Grid grida = new Grid(sa);
        grid.print();
        grida.print();
        int idx = Form2.lineIdxToBreak(grid, Line.COL);
        Grid[] cuted = Form2.cutItAt(grid, idx, Line.COL);
        // cuted[0].print();
        // cuted[1].print();
        assertEquals(grida, cuted[0]);
    }

    @Test
    public void test15() {
        String s = Filereader.stringreader("src/Problem1/Form2/test4.txt");
        String s2 = Filereader.stringreader("src/Problem1/Form2/test1.txt");
        Grid grid = new Grid(s);
        Grid grid2 = new Grid(s2);
        Form2.Import(grid);
        Form2.Import(grid2);

    }

    @Test
    public void test16() {
        String s = Filereader.stringreader("src/Problem1/Form2/test3.txt");
        Grid grid = new Grid(s);
        Grid gridAcu = new Data(grid).gridBuilder();
        gridAcu.print();
        assertEquals(gridAcu, grid);
    }

    

    @Test
    public void test17() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem1/Form1/test4.txt");
        tree = Form1.Import(in);
        out = Form1.Export(tree);
        tree.printTree();
        assertEquals(tree.valid(), true);
        assertEquals(in,out);
    }

    
}
