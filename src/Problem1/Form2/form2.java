package Problem1.Form2;

import java.util.ArrayList;

import Problem1.Tree.Node;
import Utils.*;

public class form2 {
    public static void main(String[] args) {
        String str = Filereader.stringreader("src/Problem1/Form2/test.txt");
        CGrid grid = StrToGrid.strToGrid(str);
        PrintingArrayList.printCGrid(grid);
        Import(grid, false);
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

        CLGrid afterCut = cutItAt(grid, cutIdx, !invert);
        System.out.println("AfterCut = ");
        PrintingArrayList.printCLgrid(afterCut);
        CGrid firstGrid = new CGrid(afterCut.get(0));
        CGrid secondGrid = new CGrid(afterCut.get(1));
        Import(firstGrid, !inverted);
        // Import(secondGrid);

    }

    public static Integer lineIdxToBreak(CGrid grid, Boolean inverted) {
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

    public static CGrid invertGrid(CGrid grid) {
        int j = 0;
        CGrid invertedGrid = new CGrid(grid.size());
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

    public static CLGrid cutItAt(CGrid beforCut, int cutIdx, Boolean inverted) {
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
        PrintingArrayList.printCGrid(beforCut);
        System.out.println("First Cut = ");
        PrintingArrayList.printCGrid(out.get(0));
        System.out.println("Second Cut = ");
        PrintingArrayList.printCGrid(out.get(1));
        //
        return out;
    }

    // Here starts Soud work //

    public static CGrid Export(Node root) {
        if (root == null) {
            return null;
        }
        if (root.data.type != '-' && root.data.type != '|') {
            return root.data.gridBuilder();
        }
        return Merger(Export(root.left), Export(root.right), root.data.type);
    }

    public static CGrid Merger(CGrid a,
            CGrid b, char type) {
        if (type == '|') {
            int rows = a.size();
            CGrid c = new CGrid(rows);
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
            CGrid c = new CGrid(row);
            for (int j = 0; j < a.size() - 1; j++)
                c.add(a.get(j));
            for (int j = 0; j < b.size(); j++)
                c.add(b.get(j));
            return c;
        }
        return null;
    }
}