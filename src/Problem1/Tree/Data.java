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

    public ArrayList<String> gridbuilder(){
        ArrayList<String>ans= new ArrayList<>();
        String a=String.valueOf(this.type);
        for(int i=0;i<this.length;i++){
            a=String.valueOf(this.type);
            for(int j=0;j<this.width;j=j+1){
                if(i==0||i==this.length-1){
                    a=a+"-";
                }
                else{
                if(j==0||j==this.width-1){
                    a=a+"|";
                }else if(i==1&&j==1){
                    a=a+String.valueOf(this.type) ;
                }else  a=a+".";
            }
            }
            ans.add(a);
        }
        return ans;
    }

}
