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
                a.add(s.substring(k, i-1));
                k = i;
            }
        }
        System.out.println(a);
        for (String st : a){
            if (!st.contains(".")) {
                System.out.println(st);
            }
        }
    }

    public void importString(ArrayList<String> in) {

    }
}
