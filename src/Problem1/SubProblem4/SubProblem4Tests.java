package Problem1.SubProblem4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Problem1.Tree.Node;
import Utils.Filereader;

public class SubProblem4Tests {
    @Test
    public void test1() {
        String in = Filereader.stringreader("src/Problem1/SubProblem4/test1.txt");
        ArrayList<Node> rectangels = SubProblem4.Import(in);
        ArrayList<Node> validTrees = SubProblem4.validTrees(rectangels);
        assertEquals(validTrees.size(), 1);
    }

    @Test
    public void test2() {
        String in = Filereader.stringreader("src/Problem1/SubProblem4/test2.txt");
        ArrayList<Node> rectangels = SubProblem4.Import(in);
        ArrayList<Node> validTrees = SubProblem4.validTrees(rectangels);
        assertEquals(validTrees.size(), 2);
    }

    @Test
    public void test3() {
        String in = Filereader.stringreader("src/Problem1/SubProblem4/test3.txt");
        ArrayList<Node> rectangels = SubProblem4.Import(in);
        ArrayList<Node> validTrees = SubProblem4.validTrees(rectangels);
        assertEquals(validTrees.size(), 12);
    }

    @Test
    public void test4() {
        String in = Filereader.stringreader("src/Problem1/SubProblem4/test4.txt");
        ArrayList<Node> rectangels = SubProblem4.Import(in);
        SubProblem4.validTrees(rectangels);
    }

    @Test
    public void test5() {
        String in = Filereader.stringreader("src/Problem1/SubProblem4/test1.txt");
        ArrayList<Node> rectangels = SubProblem4.Import(in);
        SubProblem4.validTrees(rectangels);
    }
}
