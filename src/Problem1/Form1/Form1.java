package Problem1.Form1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Problem1.Tree.*;
import Utils.Filereader;

public class Form1 {
    /**
     * @param s
     * @return
     */
    public static Node Import(String s) {
        if(s.charAt(s.length()-1)==']') {
            //TODO
            return new Node(new Data(s.charAt(0),parseInteger(s).get(0),parseInteger(s).get(1)));
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
        if(c=="|"){
            Data data = new Data(c,l.data.length,l.data.width+r.data.width);

        }else {
        Data data = new Data(c,data.length+r.data.length,data.width);
        }
        Node n = new Node(l, r, data);
        return n;
    }
    public static String Export(Node root) {
        // TODO
        if (root == null) {
            return null;
        }
        if (root.left != null){
            return Export(root.left);}

        if (root.right != null){
            return Export(root.right);
        }
        return root.data.printData();
            
        
    }



    public static ArrayList<Integer> parseInteger(String input) {
        ArrayList<Integer> para = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[(\\d+),");
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()) {
             para.add(Integer.parseInt(matcher.group(1)));
        }
        
        Pattern pattern2 = Pattern.compile(",(\\d+)\\]");
        Matcher matcher2 = pattern2.matcher(input);
        
        if (matcher2.find()) {
             para.add(Integer.parseInt(matcher2.group(1)));
        }
        
        return para;
    }
    
    public static void main(String[] args) throws Exception {
        String s = Filereader.stringreader("src/Problem1/Form1/test.txt");
        System.out.println(s);
        Node tree = Import(s);
        System.out.println(tree.data.type);

    }
}
