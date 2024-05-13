package Problem1.Form2;

import java.util.ArrayList;

import Utils.Filereader;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");

        ArrayList<String> lines = fromStrToLines(s);
        ArrayList<String> lines1 = fromStrToColumns(s);

        System.out.println("lines = " + lines);
        System.out.println("lines1 = " + lines1);

        letsDoIt(lines);
    }

    public static void letsDoIt(ArrayList<String> lines) {
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            if (isBreakLine(line) && lineNumber != 1 && lineNumber != lines.size()) {
                System.out.println("Line number " + lineNumber + " is full");
                cutItAt(lines, lineNumber);
                break;
            }
        }
    }

    public static ArrayList<String> fromStrToLines(String input) {
        int lastLineIdx = 0;
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < input.length(); i++)
            if (input.charAt(i) == '\n') {
                lines.add(input.substring(lastLineIdx, i - 1));
                lastLineIdx = i + 1;
            }
        return lines;
    }

    public static ArrayList<String> fromStrToColumns(String input) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; i < input.length(); j++)
                System.out.println(" ");
        }
        return lines;
    }

    public static Boolean isBreakLine(String line) {
        for (char c : line.toCharArray()) {
            if (c != '+' && c != '-')
                return false;
        }
        return true;
    }

    public static Boolean isBreakColumn(String line) {
        for (char c : line.toCharArray()) {
            if (c != '+' && c != '-')
                return false;
        }
        return true;
    }

    public static void cutItAt(ArrayList<String> arrList, int lineNum) {
        int lineNumber = 0;
        ArrayList<String> aList1 = new ArrayList<>();
        ArrayList<String> aList2 = new ArrayList<>();
        for (String line : arrList) {
            lineNumber++;
            if (lineNum >= lineNumber)
                aList1.add(line);
            if (lineNum <= lineNumber)
                aList2.add(line);
        }
        System.out.println("aList1 = " + aList1);
        System.out.println("aList2 = " + aList2);
        letsDoIt(aList1);
        letsDoIt(aList2);
    }

    public void Import(ArrayList<String> in) {

    }
}
