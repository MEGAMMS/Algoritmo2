package Problem1;

import java.util.ArrayList;
import java.util.List;

public class RectangleMerger {

    public static class SmallRectangle {
        String name;
        int length;
        int width;

        public SmallRectangle(String name, int length, int width) {
            this.name = name;
            this.length = length;
            this.width = width;
        }
    }

    public static class Result {
        boolean isPossible;
        String description;

        public Result(boolean isPossible, String description) {
            this.isPossible = isPossible;
            this.description = description;
        }
    }

    public static Result canMergeRectangles(List<SmallRectangle> rectangles) {
        if (rectangles == null || rectangles.isEmpty()) {
            return new Result(false, "No rectangles provided.");
        }

        int totalLength = 0;
        int maxWidth = 0;

        for (SmallRectangle rect : rectangles) {
            totalLength += rect.length;
            if (rect.width > maxWidth) {
                maxWidth = rect.width;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Merged Rectangle: ").append(totalLength).append("x").append(maxWidth).append("\n");
        for (SmallRectangle rect : rectangles) {
            sb.append(rect.name).append("[").append(rect.length).append(",").append(rect.width).append("] ");
        }

        return new Result(true, sb.toString());
    }

    public static void main(String[] args) {
        List<SmallRectangle> rectangles = new ArrayList<>();
        rectangles.add(new SmallRectangle("A", 20, 10));
        rectangles.add(new SmallRectangle("B", 20, 10));
        rectangles.add(new SmallRectangle("C", 30, 10));
        rectangles.add(new SmallRectangle("D", 30, 50));
        rectangles.add(new SmallRectangle("E", 40, 30));
        rectangles.add(new SmallRectangle("F", 40, 20));
        
        Result result = canMergeRectangles(rectangles);
        if (result.isPossible) {
            System.out.println("Rectangles can be merged: " + result.description);
        } else {
            System.out.println("Rectangles cannot be merged.");
        }
    }
}
