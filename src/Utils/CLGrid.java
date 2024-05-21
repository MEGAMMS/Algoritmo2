package Utils;

import java.util.ArrayList;

public class CLGrid extends ArrayList<ArrayList<ArrayList<Character>>> {
    public CLGrid() {

    }

    public CLGrid(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CLGrid(ArrayList arrayList) {
        super(arrayList);
    }

    @Override
    public ArrayList<ArrayList<Character>> get(int index) {
        // TODO Auto-generated method stub
        return super.get(index);
    }

    
}
