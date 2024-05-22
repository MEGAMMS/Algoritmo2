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

    static ArrayList<Character> finalGrid = new ArrayList<>();

    public static Node Import(Grid grid) {
        System.out.println("________________________");
        grid.print();
        Integer rowIdx = lineIdxToBreak(grid, Line.ROW);
        Integer colIdx = lineIdxToBreak(grid, Line.COL);
        if (rowIdx == null && colIdx == null) {
            System.err.println("done");
            return new Node(new Data(grid));
        }

        Grid[] afterCut = new Grid[2];
        System.out.println(rowIdx + " " + colIdx);
        char type = '#';
        if (rowIdx != null) {
            afterCut = cutItAt(grid, rowIdx, Line.ROW);
            type = '-';
            
        } else if (colIdx != null) {
            afterCut = cutItAt(grid, colIdx, Line.COL);
            type = '|';
        }
        Data data = new Data(type,grid.getRowsCount(),grid.getColsCount());
        Node out = new Node(Import(afterCut[0]),Import(afterCut[1]),data);
        return out;
    }

    public static Integer lineIdxToBreak(Grid grid, Line line) {
        if (line == Line.COL)
            grid.invert();
        char breakerChar = (line == Line.ROW ? '-' : '|');
        System.out.println("Checking this grid if it can be Cut by: " + line);
        System.out.println(grid.getRowsCount() + " " + grid.getColsCount());
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