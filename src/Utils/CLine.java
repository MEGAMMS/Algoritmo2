package Utils;

import java.util.ArrayList;

public class CLine extends ArrayList<Character> {
    public CLine() {

    }

    public CLine(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CLine(ArrayList ar) {
        super(ar);
    }
}
