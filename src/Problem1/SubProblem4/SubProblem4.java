package Problem1.SubProblem4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Problem1.Tree.Node;

public class SubProblem4 {
    static Set<Integer> widths = new HashSet<>();
    static Set<Integer> lengths = new HashSet<>();

    public static int countValidTrees(ArrayList<Node> nodes) {
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
            widths.add(width);
            lengths.add(length);
        }
        return countFromMaps(widthToNode, lengthToNode);
    }

    // recursive method:
    public static int countFromMaps(Map<Integer, ArrayList<Node>> widthToNode,
            Map<Integer, ArrayList<Node>> lengthToNode) {
        // base case:
        // one pair in the map,and one node in that pair:
        if (hasOneNode(widthToNode)) {
            assert (hasOneNode(lengthToNode));
            return 1;
        }

        return 0;
    }

    public static boolean hasOneNode(Map<Integer, ArrayList<Node>> map) {
        return map.size() == 1
                && map.entrySet().iterator().next().getValue().size() == 1;
    }

}
