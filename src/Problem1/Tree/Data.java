package Problem1.Tree;

import java.util.ArrayList;

public class Data {
    public char type;
    public int length;
    public int width;

    public Data(char type, int length, int width) {
        this.type = type;
        this.length = length;
        this.width = width;
    }

    @Override
    public String toString() {
        String s = this.type + "[" + String.valueOf(this.length) + "," + String.valueOf(this.width) + "]";
        return s;
    }

    public ArrayList<ArrayList<Character>> gridBuilder() {
        if (type == '-' || type == '|') {
            System.out.println("here");
            return null;
        }
        ArrayList<ArrayList<Character>> ans = new ArrayList<>(length);

        for (int i = 0; i < this.length; i++) {
            ans.add(new ArrayList<>(width));
            for (int j = 0; j < this.width; j = j + 1) {
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
