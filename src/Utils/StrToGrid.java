package Utils;

import java.util.ArrayList;

public class StrToGrid {
    public static CGrid strToGrid(String input) {
        CGrid grid = new CGrid();
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
