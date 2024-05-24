package Problem1.Tree;

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
        assert (this.isLeaf());
        Grid ans = new Grid(this.length, this.width);
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                if ((i == 0 && j == 0) || (i == this.length - 1 && j == 0) || (i == 0 && j == this.width - 1)
                        || (i == this.length - 1 && j == this.width - 1)) {
                    ans.get(i).set(j, '+');
                    continue;
                }
                if (i == 0 || i == this.length - 1) {
                    ans.get(i).set(j, '-');
                    continue;
                }
                if (j == 0 || j == this.width - 1) {
                    ans.get(i).set(j, '|');
                    continue;
                }
                if (i == 1 && j == 1) {
                    ans.get(i).set(j, this.type);
                    continue;
                }
                ans.get(i).set(j, '.');
            }
        }
        return ans;
    }

}
