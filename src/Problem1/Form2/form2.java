package Problem1.Form2;

import java.util.ArrayList;

import Problem1.Tree.Node;
import Utils.Filereader;
import Utils.PrintingArrayList;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");
        ArrayList<ArrayList<Character>> grid = fromStrToGrid(s);
        Import(grid);
    }

    static int listNumber = 0;
    static ArrayList<Character> finalArrList = new ArrayList<>();

    public static void Import(ArrayList<ArrayList<Character>> lines, Boolean inverted) {
        int cnt = 0;
        Character ch = '.';
        for (ArrayList<Character> ar : lines) {
            for (Character c : ar) {
                if ('A' <= c && c <= 'Z') {
                    cnt++;
                    ch = c;
                }
            }
        }
        if (cnt == 1 && inverted) {
            finalArrList.add(ch);
            finalArrList.add('|');
            PrintingArrayList.printCharArray(finalArrList);
        } else if (cnt == 1 && !inverted) {
            finalArrList.add(ch);
            finalArrList.add('-');
            PrintingArrayList.printCharArray(finalArrList);
        }
        Integer rowIdx = lineIdxToBreak(lines, false);
        Integer colIdx = lineIdxToBreak(lines, true);
        int cutIdx = 0;
        boolean invert = false;
        if (rowIdx != null) {
            cutIdx = rowIdx;
            invert = false;
        } else if (colIdx != null) {
            cutIdx = colIdx;
            invert = true;
        }

        ArrayList<ArrayList<ArrayList<Character>>> afterCut = cutItAt(lines, cutIdx, invert);
        Import(afterCut.get(0));
        Import(afterCut.get(1));

    }

    public static Integer lineIdxToBreak(ArrayList<ArrayList<Character>> lines, Boolean inverted) {
        for (int i = 1; i < lines.size() - 1; i++) {
            if (isBreakRowOrCol(lines.get(i), !inverted)) {
                return i;
            }
        }
        return null;
    }

    public static ArrayList<ArrayList<Character>> fromStrToGrid(String input) {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>());
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                grid.add(new ArrayList<>());
                continue;
            }
            grid.get(grid.size() - 1).add(c);
        }
        for (int j = 0; j < grid.size() - 1; j++) {
            grid.get(j).remove(grid.get(j).size() - 1);
        }
        return grid;
    }

    public static Boolean isBreakRowOrCol(ArrayList<Character> rowOrCol, Boolean isRow) {
        char breakerChar = (isRow ? '-' : '|');
        for (char c : rowOrCol) {
            if ((c != '+' && c != breakerChar))
                return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<Character>> invertArrList(ArrayList<ArrayList<Character>> line) {
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

    public static ArrayList<ArrayList<ArrayList<Character>>> cutItAt(ArrayList<ArrayList<Character>> beforCut,
            int cutIdx, Boolean inverted) {
        ArrayList<ArrayList<ArrayList<Character>>> out = new ArrayList<>();
        out.add(new ArrayList<>());
        out.add(new ArrayList<>());
        for (int i = 0; i < beforCut.size(); i++) {
            if (cutIdx >= i)
                out.get(0).add(beforCut.get(i));
            if (cutIdx <= i)
                out.get(1).add(beforCut.get(i));
        }
        // Tmp Debugging
        System.out.println("Cutting....");
        PrintingArrayList.printCharArrayArray(beforCut);
        System.out.println("First Cut = ");
        PrintingArrayList.printCharArrayArray(out.get(0));
        System.out.println("Second Cut = ");
        PrintingArrayList.printCharArrayArray(out.get(1));
        //
        return out;
    }

    public static void Import(ArrayList<ArrayList<Character>> in) {
        Import(in, false);
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