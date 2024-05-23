import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Form2.Grid;
import Problem1.Tree.Node;
import Utils.Filereader;

public class App {
    public static void main(String[] args) throws Exception {
        String form1 = Filereader.stringreader("src/Problem1/Form1/test1.txt");
        Node tree = Form1.Import("(" + form1 + ")");
        String out = Form1.Export(tree);
        out = out.substring(1, out.length() - 1);
        System.out.println("test1");

        Grid test = Form2.Export(tree);
        if (test != null)
            test.print();
        String str = Filereader.stringreader("src/Problem1/Form2/test1.txt");
        Grid grid = new Grid(str);
        Node tree2 = Form2.Import(grid);
        System.out.println(Form2.Export(tree2));
    }
}
