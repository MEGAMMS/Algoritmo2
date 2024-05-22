package Problem1.Form2;

import java.util.ArrayList;

import Problem1.Form1.Form1;
import Problem1.Tree.Data;
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
        Node tree = Import(grid);
        System.out.println(Form1.Export(tree));
        

    }

    public static Node Import(Grid grid) {
        System.out.println("________________________");
        Integer rowIdx = lineIdxToBreak(grid, Line.ROW);
        Integer colIdx = lineIdxToBreak(grid, Line.COL);
        if (rowIdx == null && colIdx == null) {
            System.out.println("done");
            return new Node(new Data(grid));
        }

        Grid[] afterCut = new Grid[2];
        char type = '#';
        if (rowIdx != null) {
            afterCut = cutItAt(grid, rowIdx, Line.ROW);
            type = '-';

        } else if (colIdx != null) {
            afterCut = cutItAt(grid, colIdx, Line.COL);
            type = '|';
        }
        Data data = new Data(type, grid.getRowsCount(), grid.getColsCount());
        Node out = new Node(Import(afterCut[0]), Import(afterCut[1]), data);
        return out;
    }

    public static Integer lineIdxToBreak(Grid grid, Line line) {
        if (line == Line.COL)
            grid.invert();
        char breakerChar = (line == Line.ROW ? '-' : '|');
        for (int i = 1; i < grid.getRowsCount() - 1; i++) {
            boolean ok = true;
            for (int j = 1; j < grid.getColsCount() - 1; j++) {
                char c = grid.get(i).get(j);
                if (c != '+' && c != breakerChar) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                if (line == Line.COL)
                    grid.invert();
                return i;
            }
        }
        if (line == Line.COL)
            grid.invert();
        return null;
    }

    public static Grid[] cutItAt(Grid beforeCut, int cutIdx, Line line) {
        Grid[] out = new Grid[2];
        out[0] = new Grid();
        out[1] = new Grid();
        if (line == Line.ROW) {
            for (int i = 0; i < beforeCut.size(); i++) {
                if (cutIdx >= i)
                    out[0].add(beforeCut.get(i));
                if (cutIdx <= i)
                    out[1].add(beforeCut.get(i));
            }
        } else if (line == Line.COL) {
            beforeCut.invert();
            for (int i = 0; i < beforeCut.size(); i++) {
                if (cutIdx >= i)
                    out[0].add(beforeCut.get(i));
                if (cutIdx <= i)
                    out[1].add(beforeCut.get(i));
            }
            beforeCut.invert();
            out[0].invert();
            out[1].invert();
        }

        // Tmp Debugging
        System.out.println("Cutting....");
        beforeCut.print();
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

    public static Grid Merger(Grid a, Grid b, char type) {
        if (type == '|') {
            int rowsa = a.size();
            int colsa = a.get(0).size();
            int rowsb = b.size();
            int colsb = b.get(0).size(); 
            Grid c = new Grid(Math.max(rowsa, rowsb)-1);
            for (int i = 0; i < Math.max(rowsa, rowsb)-1; i++) {
                ArrayList<Character> row = new ArrayList<>(colsa + colsb -1);
                if (i < rowsa)
                    row.addAll(a.get(i));
                if (i < rowsb) {
                    row.addAll(b.get(i));
                }
                c.add(row);
            }
            return c;
        } else if (type == '-') {
            int rowsa = a.size();
            int rowsb = b.size();
            Grid c = new Grid(rowsa + rowsb - 1);
            for (int i = 0; i < rowsa-1; i++) {
                c.add(a.get(i));
            }
            for (int i = 0; i < rowsb; i++) {
                c.add(b.get(i));
            }
            return c;
        }
        return null;
    }

}