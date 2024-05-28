package Problem1.Form2;

import Problem1.Tree.Data;
import Problem1.Tree.Node;

public class Form2 {

    public static Node Import(Grid grid) {
        Integer rowIdx = lineIdxToBreak(grid, Line.ROW);
        Integer colIdx = lineIdxToBreak(grid, Line.COL);
        if (rowIdx == null && colIdx == null) {
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
        Data data = new Data(type, grid.getColsCount(), grid.getRowsCount());
        Node out = new Node(Import(afterCut[0]), Import(afterCut[1]), data);
        return out;
    }

    public static Integer lineIdxToBreak(Grid grid, Line line) {
        if (line == Line.COL)
            grid.invert();
        char breakerChar = (line == Line.ROW ? '-' : '|');
        for (int i = 0; i < grid.getRowsCount() - 1; i++) {
            boolean ok = true;

            for (int j = 0; j < grid.getColsCount() - 1; j++) {
                char c1 = grid.get(i).get(j);
                char c2 = grid.get(i + 1).get(j);
                if ((c1 != '+' && c1 != breakerChar) || (c2 != '+' && c2 != breakerChar)) {
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
                if (cutIdx < i)
                    out[1].add(beforeCut.get(i));
            }
        } else if (line == Line.COL) {
            beforeCut.invert();
            for (int i = 0; i < beforeCut.size(); i++) {
                if (cutIdx >= i)
                    out[0].add(beforeCut.get(i));
                if (cutIdx < i)
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
        if (root.isLeaf()) {
            return root.data.gridBuilder();
        }
        return Merger(Export(root.left), Export(root.right), root.data.type);
    }

    public static Grid Merger(Grid a, Grid b, char type) {
        if (a == null || b == null) {
            return null;
        }
        int rowsA = a.size();
        int colsA = a.get(0).size();
        int rowsB = b.size();
        int colsB = b.get(0).size();
        if (type == '|') {
            assert (rowsA == rowsB);
            Grid out = new Grid(rowsA, colsA + colsB);
            for (int i = 0; i < out.getRowsCount(); i++) {
                for (int j = 0; j < out.getColsCount(); j++) {
                    if (j < colsA) {
                        out.get(i).set(j, a.get(i).get(j));
                        continue;
                    }
                    if (j >= colsA) {
                        out.get(i).set(j, b.get(i).get(j - colsA));
                        continue;
                    }
                }
            }
            return out;
        } else if (type == '-') {
            assert (colsA == colsB);
            Grid out = new Grid(rowsA + rowsB, colsA);

            for (int i = 0; i < out.getRowsCount(); i++) {
                for (int j = 0; j < out.getColsCount(); j++) {
                    if (i < rowsA) {
                        out.get(i).set(j, a.get(i).get(j));
                        continue;
                    }
                    if (i >= rowsA) {
                        out.get(i).set(j, b.get(i - rowsA).get(j));
                        continue;
                    }
                }
            }

            return out;
        }
        return null;
    }

}