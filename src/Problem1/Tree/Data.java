package Problem1.Tree;

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

}
