package Problem1.SubProblem4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Problem1.Tree.Node;

public class SubProblem4 {
    public static int countValidTrees(ArrayList<Node> nodes) {
        Map<Integer, ArrayList<Node>> widthToNode = new HashMap<>();
        Map<Integer, ArrayList<Node>> lengthToNode = new HashMap<>();
        // TODO: fill the maps with the right values

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
        // TODO: creat 2 new maps after a posible merge

        return 0;
    }

    public static boolean hasOneNode(Map<Integer, ArrayList<Node>> map) {
        return map.size() == 1
                && map.entrySet().iterator().next().getValue().size() == 1;
    }

}
