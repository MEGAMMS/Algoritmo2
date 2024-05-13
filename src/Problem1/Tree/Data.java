package Problem1.Tree;

public class Data {
    char type;
    int length;
    int width;

    public Data(char type, int length, int width) {
        this.type = type;
        this.length = length;
        this.width = width;
    }
    public String printData(){
        String s= this.type+"["+String.valueOf(this.length)+","+String.valueOf(this.width)+"]";
        return s;
    }
    
}


