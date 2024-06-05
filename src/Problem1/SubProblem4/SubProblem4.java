package Problem1.SubProblem4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Problem1.Tree.Data;
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
        int newWidth = 0;
        int newLength = 0;
        for (ArrayList<Node> ar : widthToNode.values()) {
            newWidth = 0;
            newLength = 0;
            System.out.println("ar = "+ar);
            for (Node node : ar) {
                int width = node.data.width;
                int length = node.data.length;
                newLength += length;
                newWidth = width;
            }
            ar.clear();
            ar.add(new Node(new Data('-', newWidth, newLength)));
        }
        for (ArrayList<Node> ar : lengthToNode.values()) {
            newWidth = 0;
            newLength = 0;
            System.out.println("ar = "+ar);
            for (Node node : ar) {
                int width = node.data.width;
                int length = node.data.length;
                newWidth += width;
                newLength = length;
            }
            ar.clear();
            ar.add(new Node(new Data('|', newWidth, newLength)));
        }

        // for (ArrayList<Node> Lar : lengthToNode.values())
        // for (Node Lnode : Lar)
        // for (ArrayList<Node> War : widthToNode.values())
        // for (Node Wnode : War){
        // System.out.println(Lnode.toString());
        // System.out.println(Wnode.toString());
        // if (Lnode.toString() == Wnode.toString()) {
        // System.err.println("i`ll delete " + Lnode + " or number " +
        // Lnode.data.length);
        // lengthToNode.remove(Lnode.data.length);
        // }
        // }

        // System.out.println(widthToNode);
        // System.out.println(lengthToNode);
        widthToNode.putAll(lengthToNode);
        ArrayList<Node> mergedNodes = new ArrayList<>();
        for (Integer key : widthToNode.keySet())
            mergedNodes.addAll(widthToNode.get(key));
        System.out.println(mergedNodes);
        return 0;
    }

    public static boolean hasOneNode(Map<Integer, ArrayList<Node>> map) {
        return map.size() == 1
                && map.entrySet().iterator().next().getValue().size() == 1;
    }

}
