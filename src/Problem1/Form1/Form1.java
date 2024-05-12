package Problem1.Form1;

import Problem1.Tree.Node;
import Utils.Filereader;

public class Form1 {
    public static Node Import(String s) {
        if(s.length() == 1) {
            return new Node(0);
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
            if (s.charAt(i) == '|' && cnt == 0) {
                pos = i;
                break;
            }
            if (s.charAt(i) == '-' && cnt == 0) {
                pos = i;
                break;
            }

        }
        char c = s.charAt(pos);
        String sl = s.substring(1, pos - 2);
        String sr = s.substring(pos + 2, s.length() - 1);
        Node l = Import(sl);
        Node r = Import(sr);
        Node n = new Node(c, l, r);
        return n;
    }

    public static void main(String[] args) throws Exception {
        String s = Filereader.stringreader("src/Problem1/Form1/test.txt");
        Import(s);

    }
}
