package Utils;

import java.util.ArrayList;

public class FromStrToGrid {
    public static ArrayList<ArrayList<Character>> fromStrToGrid(String input) {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>());
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                grid.add(new ArrayList<>());
                continue;
            }
            grid.get(grid.size() - 1).add(c);
        }
        for (int j = 0; j < grid.size() - 1; j++)
            grid.get(j).remove(grid.get(j).size() - 1);
        return grid;
    }
}
