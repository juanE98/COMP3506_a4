import java.util.*;

public class FactChecker {

    /**
     * Checks if a list of facts is internally consistent. That is, can they all
     * hold true at the same time? Or are two (or potentially more) facts
     * logically incompatible?
     *
     * @param facts list of facts to check consistency of
     *
     * @return true if all the facts are internally consistent, otherwise false.
     */
    public static boolean areFactsConsistent(List<Fact> facts) {
        //Adjacency list representation of digraph.
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        for (Fact fact : facts) {
            ArrayList<String> neighbours;
            String vertex;
            String neighbour;
            if (fact.getType() == Fact.FactType.TYPE_ONE) {
                vertex = fact.getPersonB();
                neighbour = fact.getPersonA();
            } else {
                vertex = fact.getPersonA();
                neighbour = fact.getPersonB();
            }

            if (adjList.containsKey(vertex)) {
                neighbours = adjList.get(vertex);
                neighbours.add(neighbour);
            } else {
                neighbours = new ArrayList<>();
                neighbours.add(neighbour);
                adjList.put(vertex, neighbours);
            }
        }

        if (isCyclic(adjList)) {
            return false;
        }
        return true;
    }

    /**
     * Helper method to check if graph is cyclic.
     * @param node starting node
     * @param visitedNodes visited nodes
     * @param stack
     * @param adjList graph representation
     * @return
     */
    private static boolean isCyclicCheck (String node,
                                          HashMap<String, Boolean> visitedNodes,
                                          HashMap<String, Boolean> stack, HashMap<String,
            ArrayList<String>> adjList) {
        if (stack.get(node) != null && visitedNodes.get(node) != null) {
            if (stack.get(node)) {
                return true;
            }
            if (visitedNodes.get(node)) {
                return false;
            }
            visitedNodes.put(node, true);
            stack.put(node, true);

            ArrayList<String> children = adjList.get(node);

            for (String c : children) {
                if (isCyclicCheck(c, visitedNodes, stack, adjList)) {
                    return true;
                }
            }
        }

        stack.put(node,false);
        return false ;
    }

    /**
     * Method to check if graph is cyclic.
     * @param adjList graph representation
     * @return
     */
    private static boolean isCyclic (HashMap<String, ArrayList<String>> adjList) {
        HashMap<String, Boolean> visitedNodes = new HashMap<>();
        HashMap<String, Boolean> stack = new HashMap<>();
        for (String nodes : adjList.keySet()) {
            visitedNodes.put(nodes, false);
            stack.put(nodes, false);
        }

        for (String node : adjList.keySet()) {
            if (isCyclicCheck(node, visitedNodes, stack, adjList)) {
                return true;
            }
        }
        return false;
    }

}
