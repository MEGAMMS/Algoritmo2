package Problem1.Form1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Problem1.Tree.*;

// a function to create the tree
public class Form1 {
    public static Node Import(String s) {
        return _Import("(" + s + ")");
    }

    /**
     * @param s
     * @return
     */
    private static Node _Import(String s) {
        if (s.charAt(s.length() - 1) == ']') {
            return new Node(new Data(s.charAt(0), parseInteger(s).get(0), parseInteger(s).get(1)));
        }
        s = s.substring(1, s.length() - 1);
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
        String sl = s.substring(0, pos - 1);
        String sr = s.substring(pos + 2, s.length());
        Node l = _Import(sl);
        Node r = _Import(sr);
        Data data;
        if (c == '|') {
            assert (l.data.length == r.data.length);
            data = new Data(c, l.data.width + r.data.width, l.data.length);
        } else {
            assert (l.data.width == r.data.width);
            data = new Data(c, l.data.width, l.data.length + r.data.length);
        }
        Node n = new Node(l, r, data);
        return n;
    }

    public static String Export(Node root) {
        String out = _Export(root);
        return out.substring(1, out.length() - 1);
    }

    // a function to create the string of a tree
    private static String _Export(Node root) {

        if (root == null) {
            return null;
        }
        if (root.isLeaf()) {
            return root.data.toString();
        }

        return '(' + _Export(root.left) + ' ' + root.data.type + ' ' + _Export(root.right) + ')';
    }

    // a function to parse integers from a string
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
}
