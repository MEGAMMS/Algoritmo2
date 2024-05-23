package Problem1.Form2;

import java.util.ArrayList;

import Problem1.Tree.Data;
import Problem1.Tree.Node;

enum Line {
    ROW,
    COL
}

public class Form2 {
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
        if (a == null || b == null) {
            return null;
        }
        if (type == '|') {
            int rowsA = a.size();
            int colsA = a.get(0).size();
            int rowsB = b.size();
            int colsB = b.get(0).size();
            int maxRows = Math.max(rowsA, rowsB);
            Grid c = new Grid(maxRows);
            for (int i = 0; i < maxRows; i++) {
                ArrayList<Character> row = new ArrayList<>(colsA + colsB);
                if (i < rowsA)
                    row.addAll(a.get(i).subList(0, colsA - 1));
                try {
                    row.addAll(a.get(i).subList(colsA - 2, colsA - 1));
                } catch (Exception e) {
                    System.out.println("Can not draw like that f**** rectangle");
                    return null;
                }
                if (i < rowsB) {
                    row.addAll(b.get(i).subList(0, colsB));
                }
                c.add(row);
            }
            return c;
        } else if (type == '-') {
            int rowsA = a.size();
            int rowsB = b.size();
            Grid c = new Grid(rowsA + rowsB - 1);
            for (int i = 0; i < rowsA - 1; i++) {
                c.add(a.get(i));
            }
            try {
                c.add(a.get(rowsA - 2));
            } catch (Exception e) {
                System.out.println("Can not draw like that f**** rectangle");
                return null;
            }
            for (int i = 0; i < rowsB; i++) {
                c.add(b.get(i));
            }
            return c;
        }
        return null;
    }

}