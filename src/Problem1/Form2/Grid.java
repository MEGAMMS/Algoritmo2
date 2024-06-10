package Problem1.Form2;

import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Character>> {
    public Grid() {
        super();
    }

    public Grid(int rowsCount, int colsCount) {
        super(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            ArrayList<Character> row = new ArrayList<>(colsCount);
            for (int j = 0; j < colsCount; j++) {
                row.add(' '); // Initialize with a default character, for example, a space
            }
            this.add(row);
        }

    }

    public Grid(String input) {
        this.add(new ArrayList<>());
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                this.add(new ArrayList<>());
                continue;
            }
            this.get(this.size() - 1).add(c);
        }

    }

    public int getRowsCount() {
        return this.size();
    }

    public int getColsCount() {
        if (this.size() == 0)
            return 0;
        return this.get(0).size();
    }

    public Grid(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Grid(ArrayList arrayList) {
        super(arrayList);
    }

    public void print() {
        System.out.println(this.toString());
    }

    public void invert() {
        int j = 0;
        Grid invertedGrid = new Grid(this.size());
        for (int i = 0; i < this.size(); i++) {
            j = 0;
            for (char c : this.get(i)) {
                if (i == 0)
                    invertedGrid.add(new ArrayList<Character>());
                invertedGrid.get(j).add(c);
                j++;
            }
        }
        this.clear();
        this.addAll(invertedGrid);
    }

    @Override
    public String toString() {
        String out = "";
        for (ArrayList<Character> ar : this) {
            for (Character c : ar) {
                out += c;
            }
            if (out.charAt(out.length() - 1) != '\n')
                out += '\n';
        }
        out.replace("\r", "");
        return out;
    }

}
