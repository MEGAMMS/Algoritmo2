package Utils;

import java.util.ArrayList;

public class CGrid extends ArrayList<ArrayList<Character>> {
    public CGrid() {

    }

    public CGrid(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CGrid(ArrayList arrayList) {
        super(arrayList);
    }

    public void print() {
        for (ArrayList<Character> ac : this) {
            PrintingArrayList.printCharArray(ac);
        }
    }
}
