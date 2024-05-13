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

    public String gridbuilder(){
        String ans="-";
        if(this.type != '-'){

            for(int i=0;i<this.length;i=i+4){
                ans=ans+"-";
            }
            return ans;
        }
        ans="|"+this.type;
        for(int i=0;i<this.length;i=i+4){
            ans=ans+".";
        }
        return ans;
    }

}
