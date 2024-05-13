package Problem1.Form2;

import java.util.ArrayList;

import javax.print.DocFlavor.CHAR_ARRAY;

import Problem1.Tree.Node;
import Utils.Filereader;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");

        ArrayList<String> lines = fromStrToLines(s);
        ArrayList<String> columns = fromStrToColumns(s);

        System.out.println("lines = " + lines);
        System.out.println("Columns = " + columns);

        letsDoIt(lines/*, columns*/);
    }

    public static void letsDoIt(ArrayList<String> lines/*, ArrayList<String> columns */) {
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            if (isBreakLine(line) && lineNumber != 1 && lineNumber != lines.size()) {
                System.out.println("Line number " + lineNumber + " is full");
                cutItAt(lines, lineNumber);
                break;
            }
        }

        // int colNumber = 0;
        // for (String column : columns) {
        //     colNumber++;
        //     if (isBreakLine(column) && colNumber != 1 && colNumber != columns.size()) {
        //         System.out.println("Column number " + colNumber + " is full");
        //         cutItAt(columns, colNumber);
        //         break;
        //     }
        // }
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
        int counterForLater = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '\n') {
                lines.add(input.charAt(i) + "");
                counterForLater++;
            } else
                break;
        }
        int trackCounter = 0;
        for (int i = counterForLater; i < input.length(); i++) {
            if (input.charAt(i) != '\n') {
                lines.set(trackCounter, lines.get(trackCounter) + input.charAt(i));
                trackCounter++;
            } else
                trackCounter = 0;
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
            if (c != '+' && c != '|')
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
    
    public static ArrayList<String> g=new ArrayList<>();
    public static String Export(Node root) {

        if (root == null) {
            return null;
        }
        
        g.add(root.data.gridbuilder());       
        return Export(root.left)+Export(root.right);
    }
}
