package Problem1.Form2;

import java.util.ArrayList;

import Problem1.Tree.Node;
import Utils.*;

enum Line {
    ROW,
    COL
}

public class Form2 {
    public static void main(String[] args) {
        String str = Filereader.stringreader("src/Problem1/Form2/test.txt");
        Grid grid = new Grid(str);
        Import(grid);
    }

    static int listNumber = 0;
    static ArrayList<Character> finalGrid = new ArrayList<>();

    public static void Import(Grid grid) {

        Integer rowIdx = lineIdxToBreak(grid, Line.ROW);
        Integer colIdx = lineIdxToBreak(grid, Line.COL);
        Grid[] afterCut = new Grid[2];
        boolean done = false;

        grid.print();
        System.out.println(rowIdx + " " + colIdx);
        if (rowIdx != null) {
            afterCut = cutItAt(grid, rowIdx, Line.ROW);
        } else if (colIdx != null) {
            afterCut = cutItAt(grid, colIdx, Line.COL);
        } else {
            int cnt = 0;
            Character ch = '.';
            System.out.println("heeeeeeeelp  meeeeeeee");
            for (ArrayList<Character> ar : grid) {
                for (Character c : ar) {
                    if ('A' <= c && c <= 'Z') {
                        cnt++;
                        ch = c;
                        done = true;
                    }
                }
            }
            if (cnt == 1) {
                finalGrid.add(ch);
                finalGrid.add('|');
                PrintingArrayList.printCharArray(finalGrid);
            }
        }
        
        System.out.println("AfterCut = ");
        for (Grid grid2 : afterCut) {
            grid2.print();
        }
        Grid firstGrid = new Grid(afterCut[0]);
        Grid secondGrid = new Grid(afterCut[1]);

        // if (!done) {
        //     Import(firstGrid);
        // }
        // Import(secondGrid);
    }

    public static Integer lineIdxToBreak(Grid grid, Line line) {
        char breakerChar = (line == Line.ROW ? '-' : '|');
        for (int i = 1; i < grid.getColsCount() - 1; i++) {
            boolean ok = true;
            for (int j = 1; j < grid.getRowsCount() - 1; j++) {
                char c = (line == Line.ROW ? grid.get(i).get(j) : grid.get(j).get(i));
                if ((c != '+' && c != breakerChar))
                    ok = false;
            }
            if (ok)
                return i;
        }
        return null;
    }

    public static Grid[] cutItAt(Grid beforCut, int cutIdx, Line line) {
        Grid[] out = new Grid[2];
        out[0] = new Grid();
        out[1] = new Grid();
        if (line == Line.ROW) {
            for (int i = 0; i < beforCut.size(); i++) {
                if (cutIdx >= i)
                    out[0].add(beforCut.get(i));
                if (cutIdx <= i)
                    out[1].add(beforCut.get(i));
            }
        } else if (line == Line.COL) {
            beforCut.invert();
            for (int i = 0; i < beforCut.size(); i++) {
                if (cutIdx >= i)
                    out[0].add(beforCut.get(i));
                if (cutIdx <= i)
                    out[1].add(beforCut.get(i));
            }
            beforCut.invert();
            out[0].invert();
            out[1].invert();
        }

        // Tmp Debugging
        System.out.println("Cutting....");
        beforCut.print();
        System.out.println("First Cut = ");
        out[0].print();
        System.out.println("Second Cut = ");
        out[1].print();
        return out;
    }

    // Here starts Soud work //

    public static Grid Export(Node root) {
        if (root == null) {
            return null;
        }
        if (root.data.type != '-' && root.data.type != '|') {
            return root.data.gridBuilder();
        }
        return Merger(Export(root.left), Export(root.right), root.data.type);
    }

    public static Grid Merger(Grid a,
            Grid b, char type) {
        if (type == '|') {
            int rows = a.size();
            Grid c = new Grid(rows);
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
            Grid c = new Grid(row);
            for (int j = 0; j < a.size() - 1; j++)
                c.add(a.get(j));
            for (int j = 0; j < b.size(); j++)
                c.add(b.get(j));
            return c;
        }
        return null;
    }
}