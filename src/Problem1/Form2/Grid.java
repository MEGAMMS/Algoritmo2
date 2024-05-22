package Problem1.Form2;

import java.util.ArrayList;

import Utils.PrintingArrayList;

public class Grid extends ArrayList<ArrayList<Character>> {
    public Grid() {

    }
    public int getRowsCount(){
        return this.size();
    }
    public int getColsCount(){
        if(this.size() == 0)return 0;
        return this.get(0).size();
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
        for (int j = 0; j < this.size() - 1; j++)
            this.get(j).remove(this.get(j).size() - 1);
    }

    public Grid(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Grid(ArrayList arrayList) {
        super(arrayList);
    }

    public void print() {
        for (ArrayList<Character> ac : this) {
            PrintingArrayList.printCharArray(ac);
        }
    }

}
