package Problem1.Form2;

import java.util.ArrayList;

import Utils.Filereader;

public class form2 {
    public static void main(String[] args) {
        String s = Filereader.stringreader("src/Problem1/Form2/test.txt");
        
        ArrayList<String> lines = fromStrToLines(s);

        System.out.println(lines);

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            if (isBreakLine(line))
                System.out.println(line + "Line number " + lineNumber +" : is full");
        }
    }


    public static ArrayList<String> fromStrToLines(String input){
        int lastLineIdx = 0;
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                lines.add(input.substring(lastLineIdx, i - 1));
                lastLineIdx = i + 1;
            }
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

    public void Import(ArrayList<String> in) {

    }
}
