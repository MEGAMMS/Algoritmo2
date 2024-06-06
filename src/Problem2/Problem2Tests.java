package Problem2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Problem2.Tree.Node;
import Utils.Filereader;

public class Problem2Tests {

    @Test
    public void test1() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem2/test.txt");
        tree = Problem2.Import(in);
        out = Problem2.Export(tree);
        assertEquals(in, out);
    }

    @Test
    public void test2() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem2/test2.txt");
        tree = Problem2.Import(in);
        out = Problem2.Export(tree);
        assertEquals(out, in);
    }

    @Test
    public void test3() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem2/test3.txt");
        tree = Problem2.Import(in);
        out = Problem2.Export(tree);
        assertEquals(out, in);
    }

    @Test
    public void test4() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem2/test4.txt");
        tree = Problem2.Import(in);
        out = Problem2.Export(tree);
        assertEquals(out, in);
    }

    @Test
    public void test5() {
        String in, out, expected;
        Node tree;
        in = Filereader.stringreader("src/Problem2/test.txt");
        tree = Problem2.Import(in);
        out = Problem2.BinaryConverter(tree);
        expected = Filereader.stringreader("src/Problem2/binarytest2.txt");
        assertEquals(out, expected);
    }

    @Test
    public void test6() {
        String in, out;
        Node tree;
        in = Filereader.stringreader("src/Problem2/test.txt");
        tree = Problem2.Import(in);
        Node mid = Problem2._BinaryConverter(tree);
        out = Problem2.KaryTreeConverter(mid);
        assertEquals(out, in);
    }
}
