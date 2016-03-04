import java.util.ArrayList;
import java.util.Queue; //for breadth first search
import java.util.Stack;

/**
 *
 * @author brad
 */

public class BFSearch {
    public static void search(char[] board) {
        SearchNode root = new SearchNode(new TileGameState(board));
        Stack<SearchNode> stack = new Stack<SearchNode>();
        
        stack.add(root);
        performSearch(stack);
    }
    private static boolean checkRepeats(SearchNode n) {
        boolean retValue = false;
        SearchNode checkNode = n;
        while(n.getParent() != null && ! retValue) {
            if(n.getParent().getCurrentState().equals(checkNode.getCurrentState())) {
                retValue = true;
            }
            n = n.getParent();
        }
        return retValue;
    }
    public static void performSearch(Stack<SearchNode> s) {
        int searchCount = 1;
        while(!s.isEmpty()) {
            System.out.println("Stack:" + searchCount);
            SearchNode tempNode = (SearchNode) s.pop(); 
            if(!tempNode.getCurrentState().isGoal()) {
                //get the list of successors from the tempstate to tempnode
                ArrayList<State> tempSuccessors = tempNode.getCurrentState().gen_children();
                for (int i=0; i < tempSuccessors.size(); i++) {
                    SearchNode newNode = new SearchNode(tempNode, tempSuccessors.get(i));
                    if(!checkRepeats(newNode)) {
                        s.add(newNode);
                    }
                }
                searchCount++;
            } else { //Winner Found!
                Stack<SearchNode> solutionPath = new Stack<SearchNode>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();
                while(tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);
                
                int loopsize = solutionPath.size();
                
                for(int i=0; i < loopsize; i++) {
                    tempNode = solutionPath.pop();
                    tempNode.getCurrentState().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The numnber of nodes examined: " + searchCount );
                System.exit(0);
            }
        }
    }
}
