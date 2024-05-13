package Problem1.Form1;

import Problem1.Tree.*;
import Utils.Filereader;

public class Form1 {
    public static Node Import(String s) {
        if(s.length() == 5) {
            //TODO
            return new Node(new Data(s.charAt(0), -1, -1));
        }
        int cnt = 0;
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            }
            if (s.charAt(i) == ')') {
                cnt--;
            }
            if ((s.charAt(i) == '|' || s.charAt(i) == '-') && cnt == 0) {
                pos = i;
                break;
            }

        }
        char c = s.charAt(pos);
        String sl = s.substring(1, pos - 2);
        String sr = s.substring(pos + 2, s.length() - 1);
        Node l = Import(sl);
        Node r = Import(sr);
        //TODO
        Data data = new Data(c,-1,-1);
        Node n = new Node(l, r, data);
        return n;
    }
    public static String Export(Node root){
        //TODO
        return "";
    }



    public static void main(String[] args) throws Exception {
        String s = Filereader.stringreader("src/Problem1/Form1/test.txt");
        Import(s);

    }
}
