package Problem1.Form2;

import java.util.ArrayList;

import Utils.Filereader;
import Utils.PrintingArrayList;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");
        // System.out.println("s = \n" + s);
        ArrayList<ArrayList<Character>> lines = fromStrToGrid(s);
        Import(lines);
    }
    // int colNumber = 0;
    // for (String column : columns) {
    // colNumber++;
    // if (isBreakLine(column) && colNumber != 1 && colNumber != columns.size()) {
    // System.out.println("Column number " + colNumber + " is full");
    // cutItAt(columns, colNumber);
    // break;
    // }
    // }

    public static void letsDoIt(ArrayList<ArrayList<Character>> lines) {
        int lineNumber = 0;
        for (ArrayList<Character> ArrayList : lines) {
            lineNumber++;
            // System.out.println("I am here at line number " + lineNumber + " and it is " + isBreakLine(ArrayList) + "  "
            //         + ArrayList);
            if (isBreakLine(ArrayList) && lineNumber != 1 && lineNumber != lines.size()) {
                System.out.println("Line number: " + lineNumber + " is full lets cut the arraylist at line number: " + lineNumber);
                cutItAt(lines, lineNumber);
                break;
            }
            else if (isBreakLine(ArrayList) && lineNumber != 1 && lineNumber != lines.size()) {
                System.out.println("Line number: " + lineNumber + " is full lets cut the arraylist at line number: " + lineNumber);
                cutItAt(lines, lineNumber);
                break;
            }
        }
        System.out.println("can not to be cut more than this!");
    }

    public static ArrayList<ArrayList<Character>> fromStrToGrid(String input) {
        ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>(new ArrayList<>());
        grid.add(new ArrayList<>());
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                grid.add(new ArrayList<>());
                continue;
            }
            grid.get(grid.size() - 1).add(c);
        }
        return grid;
    }

    // public static ArrayList<ArrayList<Character>>
    // fromStrToColumns(ArrayList<Character> input) {
    // ArrayList<ArrayList<Character>> lines = new ArrayList<>();
    // int counterForLater = 0;
    // for (int i = 0; i < input.length(); i++) {
    // if (input.charAt(i) != '\n') {
    // lines.add(input.charAt(i) + "");
    // counterForLater++;
    // } else
    // break;
    // }
    // int trackCounter = 0;
    // for (int i = counterForLater; i < input.length(); i++) {
    // if (input.charAt(i) != '\n') {
    // lines.set(trackCounter, lines.get(trackCounter) + input.charAt(i));
    // trackCounter++;
    // } else
    // trackCounter = 0;
    // }
    // return lines;
    // }

    public static Boolean isBreakLine(ArrayList<Character> line) {
        int cnt = 0;
        for (char c : line) {
            cnt++;
            // System.out.println("char is : " + c);
            if (c != '+' && c != '-' && cnt != line.size())
                return false;
        }
        return true;
    }

    public static Boolean isBreakColumn(ArrayList<Character> line) {
        for (char c : line) {
            if (c != '+' && c != '|')
                return false;
        }
        return true;
    }

    public static void cutItAt(ArrayList<ArrayList<Character>> arrList, int lineNum) {
        int lineNumber = 0;
        ArrayList<ArrayList<Character>> aList1 = new ArrayList<>();
        ArrayList<ArrayList<Character>> aList2 = new ArrayList<>();
        for (ArrayList<Character> line : arrList) {
            lineNumber++;
            if (lineNum >= lineNumber)
                aList1.add(line);
            if (lineNum <= lineNumber)
                aList2.add(line);
        }
        System.out.println("aList1 = ");
        PrintingArrayList.printCharArrayArray(aList1);
        System.out.println("aList2 = ");
        PrintingArrayList.printCharArrayArray(aList2);
        letsDoIt(aList1);
        letsDoIt(aList2);
    }

    public static void Import(ArrayList<ArrayList<Character>> in) {

        // ArrayList<String> columns = fromStrToColumns(s);

        // System.out.println("lines = " + lines);
        // for (ArrayList<Character> ar : in)
        // System.out.println("ar = " + ar);
        // System.out.println("Columns = " + columns);

        letsDoIt(in);
    }
}
