package Problem1.Form2;

import java.util.ArrayList;

import Utils.Filereader;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");
        int k = 0;
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\n') {
                a.add(s.substring(k, i - 1));
                k = i;
            }
        }

        // System.out.println(a);

        a.forEach(ss -> {
            // if (isFullLine(ss)) {
            // System.out.println("full");
            // }
            for (int i = 0; i < ss.length(); i++) {

                System.out.println(ss.charAt(i) + " ");
            }
        });
    }

    public static Boolean isFullLine(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                System.out.println(s.charAt(i) + "this is false");
                // return false;
            }
        return true;
    }

    public void importString(ArrayList<String> in) {

    }
}
