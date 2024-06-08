package Problem1.SubProblem4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Problem1.Form1.Form1;
import Problem1.Form2.Form2;
import Problem1.Tree.Node;
import Utils.Filereader;

public class SubProblem4 {

    public static void main(String[] args) {
        String in = Filereader.stringreader("src/Problem1/SubProblem4/test4.txt");
        ArrayList<Node> rectangels = Import(in);
        ArrayList<Node> validTrees = SubProblem4.validTrees(rectangels);

        for (Node tree : validTrees) {
            tree.printTree();
            Form2.Export(tree).print();
        }
        System.err.println(validTrees.size());
    }

    public static ArrayList<Node> validTrees(ArrayList<Node> nodes) {
        Map<Integer, ArrayList<Node>> widthToNode = new HashMap<Integer, ArrayList<Node>>();
        Map<Integer, ArrayList<Node>> lengthToNode = new HashMap<Integer, ArrayList<Node>>();
        for (Node node : nodes) {
            int width = node.data.width;
            int length = node.data.length;
            if (!widthToNode.containsKey(width))
                widthToNode.put(width, new ArrayList<>());
            if (!lengthToNode.containsKey(length))
                lengthToNode.put(length, new ArrayList<>());
            widthToNode.get(width).add(node);
            lengthToNode.get(length).add(node);
        }
        ArrayList<Node> out = new ArrayList<>();
        countFromMaps(widthToNode, lengthToNode, out);
        return out;
    }

    // recursive method:
    public static int countFromMaps(Map<Integer, ArrayList<Node>> widthToNode,
            Map<Integer, ArrayList<Node>> lengthToNode, ArrayList<Node> trees) {
        // base case:
        // one pair in the map,and one node in that pair:
        if (hasOneNode(widthToNode)) {
            assert (hasOneNode(lengthToNode));
            Node tree = widthToNode.values().iterator().next().get(0);
            assert (tree.valid());
            trees.add(tree);
            return 1;
        }
        int ans = 0;
        // Merge by width
        ans += mergeAndCount(widthToNode, lengthToNode, '-', trees);

        // Merge by length
        ans += mergeAndCount(lengthToNode, widthToNode, '|', trees);

        return ans;
    }

    private static int mergeAndCount(Map<Integer, ArrayList<Node>> primaryMap,
            Map<Integer, ArrayList<Node>> secondaryMap,
            char mergeType, ArrayList<Node> trees) {
        int ans = 0;

        for (Map.Entry<Integer, ArrayList<Node>> entry : primaryMap.entrySet()) {
            ArrayList<Node> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (i == j)
                        continue;
                    Node first = list.get(i);
                    Node second = list.get(j);
                    Node merged = new Node(first, second, mergeType);

                    // Create deep copies of the maps
                    Map<Integer, ArrayList<Node>> newPrimaryMap = deepCopyMap(primaryMap);
                    Map<Integer, ArrayList<Node>> newSecondaryMap = deepCopyMap(secondaryMap);

                    // Remove first and second nodes from the new maps
                    removeNode(newPrimaryMap, first);
                    removeNode(newPrimaryMap, second);
                    removeNode(newSecondaryMap, first);
                    removeNode(newSecondaryMap, second);

                    // Add the merged node to the new maps
                    boolean byWidth = (mergeType == '-');
                    addNode(newPrimaryMap, merged, byWidth);
                    addNode(newSecondaryMap, merged, !byWidth);

                    // Debugging:
                    // System.err.println("MERGING : " + mergeType);
                    // System.err.println("Maps before: ");
                    // System.err.println("Prim: " + primaryMap);
                    // System.err.println("Sec: " + secondaryMap);
                    // System.err.println("First: " + first);
                    // System.err.println("Second: " + second);
                    // System.err.println("merged: " + merged);
                    // System.err.println("Maps after: ");
                    // System.err.println("New Prim: " + newPrimaryMap);
                    // System.err.println("New Sec: " + newSecondaryMap);

                    // Recursively call the method with new maps
                    if (byWidth)
                        ans += countFromMaps(newPrimaryMap, newSecondaryMap, trees);
                    else
                        ans += countFromMaps(newSecondaryMap, newPrimaryMap, trees);
                }
            }
        }

        return ans;
    }

    private static Map<Integer, ArrayList<Node>> deepCopyMap(Map<Integer, ArrayList<Node>> original) {
        Map<Integer, ArrayList<Node>> copy = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Node>> entry : original.entrySet()) {
            ArrayList<Node> originalList = entry.getValue();
            ArrayList<Node> copiedList = new ArrayList<>(originalList.size());
            for (Node node : originalList) {
                copiedList.add(new Node(node.left, node.right, node.data)); // Deep copy node
            }
            copy.put(entry.getKey(), copiedList);
        }
        return copy;
    }

    private static void removeNode(Map<Integer, ArrayList<Node>> map, Node nodeToRemove) {
        for (ArrayList<Node> list : map.values()) {
            for (Node n : list) {
                if (n.equals(nodeToRemove)) {
                    list.remove(n);
                    // Remove entries with empty lists
                    map.entrySet().removeIf(entry -> entry.getValue().isEmpty());
                    return;
                }
            }
        }
    }

    private static void addNode(Map<Integer, ArrayList<Node>> map, Node node, boolean byWidth) {
        int key = (byWidth ? node.data.width : node.data.length);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(node);
    }

    public static boolean hasOneNode(Map<Integer, ArrayList<Node>> map) {
        if (map.size() != 1)
            return false;
        ArrayList<Node> nodes = map.values().iterator().next();
        return nodes.size() == 1;
    }

    public static ArrayList<Node> Import(String s) {
        String[] lines = s.split("\n");
        ArrayList<Node> out = new ArrayList<>();
        for (String line : lines) {
            out.add(Form1.stringToNode(line));
        }
        return out;
    }

}
