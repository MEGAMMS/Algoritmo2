package Problem1.Form2;

import java.util.ArrayList;

import Problem1.Tree.Node;
import Utils.Filereader;
import Utils.PrintingArrayList;
import Utils.StrToGrid;
import Utils.CGrid;
import Utils.CLGrid;
import Utils.CLine;

public class form2 {
    public static void main(String[] args) {
        String str = Filereader.stringreader("src/Problem1/Form2/test.txt");
        CGrid grid = StrToGrid.strToGrid(str);
        PrintingArrayList.printCharArrayArray(grid);
        Import(grid);
    }

    static int listNumber = 0;
    static ArrayList<Character> finalGrid = new ArrayList<>();

    public static void Import(CGrid grid, Boolean inverted) {
        int cnt = 0;
        Character ch = '.';
        for (ArrayList<Character> ar : grid) {
            for (Character c : ar) {
                if ('A' <= c && c <= 'Z') {
                    cnt++;
                    ch = c;
                }
            }
        }
        if (cnt == 1 && inverted) {
            finalGrid.add(ch);
            finalGrid.add('|');
            PrintingArrayList.printCharArray(finalGrid);
        } else if (cnt == 1 && !inverted) {
            finalGrid.add(ch);
            finalGrid.add('-');
            PrintingArrayList.printCharArray(finalGrid);
        }
        Integer rowIdx = lineIdxToBreak(grid, false);
        Integer colIdx = lineIdxToBreak(grid, true);
        int cutIdx = 0;
        boolean invert = false;
        if (rowIdx != null) {
            cutIdx = rowIdx;
            invert = false;
        } else if (colIdx != null) {
            cutIdx = colIdx;
            invert = true;
        }

        CLGrid afterCut = cutItAt(grid, cutIdx, invert);
        CGrid zero = new CGrid(afterCut.get(0));
        CGrid one = new CGrid(afterCut.get(1));
        Import(zero);
        Import(one);

    }

    public static Integer lineIdxToBreak(ArrayList<ArrayList<Character>> grid, Boolean inverted) {
        for (int i = 1; i < grid.size() - 1; i++)
            if (isBreakRowOrCol(grid.get(i), !inverted))
                return i;
        return null;
    }

    public static Boolean isBreakRowOrCol(ArrayList<Character> rowOrCol, Boolean isRow) {
        char breakerChar = (isRow ? '-' : '|');
        for (char c : rowOrCol) {
            if ((c != '+' && c != breakerChar))
                return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<Character>> invertGrid(ArrayList<ArrayList<Character>> grid) {
        int j = 0;
        ArrayList<ArrayList<Character>> invertedGrid = new ArrayList<>(grid.size());
        for (int i = 0; i < grid.size(); i++) {
            j = 0;
            for (char c : grid.get(i)) {
                if (i == 0)
                    invertedGrid.add(new ArrayList<Character>());
                invertedGrid.get(j).add(c);
                j++;
            }
        }
        return invertedGrid;
    }

    public static CLGrid cutItAt(ArrayList<ArrayList<Character>> beforCut,
            int cutIdx, Boolean inverted) {
        CLGrid out = new CLGrid();
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

    public static void Import(CGrid in) {
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