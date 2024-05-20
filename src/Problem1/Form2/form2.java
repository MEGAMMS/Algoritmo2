package Problem1.Form2;

import java.util.ArrayList;
import Problem1.Tree.Node;
import Utils.Filereader;
import Utils.PrintingArrayList;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");
        ArrayList<ArrayList<Character>> lines = fromStrToGrid(s);
        Import(lines);
    }

    public static void letsDoIt(ArrayList<ArrayList<Character>> lines) {
        letscheck(lines);
        letscheck(lineToColumn(lines));
    }

    public static void letscheck(ArrayList<ArrayList<Character>> lines) {
        int lineNumber = 0;
        for (ArrayList<Character> ArrayList : lines) {
            lineNumber++;
            if (isBreakLine(ArrayList) && lineNumber != 1 && lineNumber != lines.size()) {
                System.out.println("Line number: " + lineNumber + " is full lets cut it at: " + lineNumber);
                cutItAt(lines, lineNumber);
                break;
            }
        }
        System.out.println("Can not be cut more than this!");
    }

    public static ArrayList<ArrayList<Character>> fromStrToGrid(String input) {
        ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>(new ArrayList<>());
        grid.add(new ArrayList<>());
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                grid.add(new ArrayList<>());
                continue;
            }
            grid.get(grid.size() - 1).add(c);
        }
        return grid;
    }

    public static Boolean isBreakLine(ArrayList<Character> line) {
        int cnt = 0;
        for (char c : line) {
            cnt++;
            if (c != '+' && c != '-' && cnt != line.size())
                return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<Character>> lineToColumn(ArrayList<ArrayList<Character>> line) {
        int j = 0;
        ArrayList<ArrayList<Character>> line2 = new ArrayList<>(line.size());
        for (int i = 0; i < line.size(); i++) {
            j = 0;
            for (char c : line.get(i)) {
                if (i == 0)
                    line2.add(new ArrayList<Character>());
                line2.get(j).add(c);
                j++;
            }
        }
        return line2;
    }

    public static void cutItAt(ArrayList<ArrayList<Character>> arrList, int lineNum) {
        int lineNumber = 0;
        ArrayList<ArrayList<Character>> aList1 = new ArrayList<>();
        ArrayList<ArrayList<Character>> aList2 = new ArrayList<>();
        for (ArrayList<Character> line : arrList) {
            lineNumber++;
            if (lineNum >= lineNumber)
                aList1.add(line);
            if (lineNum <= lineNumber)
                aList2.add(line);
        }
        System.out.println("aList1 = ");
        PrintingArrayList.printCharArrayArray(aList1);
        System.out.println("aList2 = ");
        PrintingArrayList.printCharArrayArray(aList2);
        letsDoIt(aList1);
        letsDoIt(aList2);
    }

    public static void Import(ArrayList<ArrayList<Character>> in) {
        letsDoIt(in);
    }

    // Here starts Soud work //

    public static ArrayList<String> g = new ArrayList<>();

    public static ArrayList<ArrayList<Character>> Export(Node root) {
        if (root == null) {
            return null;
        }
        if (root.data.type != '-' && root.data.type != '|') {
            return root.data.gridBuilder();
        }
        return Merger(Export(root.left), Export(root.right), root.data.type);
    }

    public static ArrayList<ArrayList<Character>> Merger(ArrayList<ArrayList<Character>> a,
            ArrayList<ArrayList<Character>> b, char type) {
        if (type == '|') {
            int rows = a.size();
            ArrayList<ArrayList<Character>> c = new ArrayList<>(rows);
            for (int i = 0; i < rows; i++) {
                int colm = a.get(i).size() + b.get(i).size() - 1;
                c.add(new ArrayList<>(colm));
                for (int j = 0; j < a.get(i).size() - 1; j++) {
                    c.get(i).add(a.get(i).get(j));
                }
                for (int j = 0; j < b.get(i).size(); j++) {
                    c.get(i).add(b.get(i).get(j));
                }
            }
            return c;
        }
        if (type == '-') {
            int row = a.size() + b.size() - 1;
            ArrayList<ArrayList<Character>> c = new ArrayList<>(row);
            for (int j = 0; j < a.size() - 1; j++)
                c.add(a.get(j));
            for (int j = 0; j < b.size(); j++)
                c.add(b.get(j));
            return c;
        }
        return null;
    }
}