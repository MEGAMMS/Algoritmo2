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

}
