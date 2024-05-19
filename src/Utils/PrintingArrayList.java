package Utils;

import java.util.ArrayList;

public class PrintingArrayList {
    public static void printCharArrayArray(ArrayList<ArrayList<Character>> array) {
        for (ArrayList<Character> ac : array) {
            printCharArray(ac);
        }
    }

    public static void printCharArray(ArrayList<Character> array) {
        for (char c : array) {
            System.out.print(c);
        }
        System.out.println();
    }
}
