package Problem1.Tree;

import java.util.ArrayList;

import Problem1.Form2.Grid;

public class Data {
    public char type;
    public int length;
    public int width;

    public Data(char type, int width, int length) {
        this.type = type;
        this.length = length;
        this.width = width;
    }

    public Data(Grid grid) {
        this.type = grid.get(1).get(1);
        this.length = grid.getRowsCount();
        this.width = grid.getColsCount();
    }

    public boolean isLeaf() {
        return this.type != '-' && this.type != '|';
    }

    @Override
    public String toString() {
        String s = this.type + "[" + String.valueOf(this.width) + "," + String.valueOf(this.length) + "]";
        return s;
    }

    public Grid gridBuilder() {
        if (type == '-' || type == '|') {
            System.out.println("here");
            return null;
        }
        Grid ans = new Grid(this.length);

        for (int i = 0; i < this.length; i++) {
            ans.add(new ArrayList<>(this.width));
            for (int j = 0; j < this.width; j = j + 1) {
                if ((i == 0 && j == 0) || (i == this.length - 1 && j == 0) || (i == 0 && j == this.width - 1)
                        || (i == this.length - 1 && j == this.width - 1)) {
                    ans.get(i).add('+');
                    continue;
                }
                if (i == 0 || i == this.length - 1) {
                    ans.get(i).add('-');
                    continue;
                }
                if (j == 0 || j == this.width - 1) {
                    ans.get(i).add('|');
                    continue;
                }
                if (i == 1 && j == 1) {
                    ans.get(i).add(this.type);
                    continue;
                }
                ans.get(i).add('.');
            }
        }
        return ans;
    }

}
