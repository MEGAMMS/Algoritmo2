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
        this.length = grid.getRowsCount();
        this.width = grid.getColsCount();
        this.type = grid.get((this.length - 1) / 2).get((this.width - 1) / 2);
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
        Grid grid = new Grid(this.length, this.width);
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                if (i == 0 || i == this.length - 1) {
                    grid.get(i).set(j, '-');
                    continue;
                }
                if (j == 0 || j == this.width - 1) {
                    grid.get(i).set(j, '|');
                    continue;
                }
                grid.get(i).set(j, ' ');
            }
        }
        grid.get(0).set(0, '+');
        grid.get(0).set(this.width - 1, '+');
        grid.get(this.length - 1).set(0, '+');
        grid.get(this.length - 1).set(this.width - 1, '+');
        grid.get((this.length - 1) / 2).set((this.width - 1) / 2, type);
        return grid;
    }

}
