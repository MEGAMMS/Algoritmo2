package Utils;

import java.util.ArrayList;

public class PrintingArrayList {
    public static void printCharArray(ArrayList<Character> array) {
        for (char c : array) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void printCGrid(ArrayList<ArrayList<Character>> array) {
        for (ArrayList<Character> ac : array) {
            printCharArray(ac);
        }
    }

    public static void printCLgrid(ArrayList<ArrayList<ArrayList<Character>>> array) {
        for (ArrayList<ArrayList<Character>> ac : array) {
            printCGrid(ac);
        }
    }
    
}
