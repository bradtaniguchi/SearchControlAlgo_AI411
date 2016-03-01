package pkg8puzzle;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author brad
 */
public class DFSearch {
    public static void search(int[] board, boolean d){
        SearchNode root = new SearchNode(new EightPuzzleState(board));
        Stack<SearchNode> stack = new Stack<SearchNode>();
        
        stack.add(root);
        performSearch(stack);
    }
    private static boolean checkRepeats(SearchNode n) {
        //helper method to see if SearchNode has already been evaluated.
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
        while(!s.isEmpty()) {//while queue is not empty
            SearchNode tempNode = (SearchNode) s.pop();// CASTING!
            if(!tempNode.getCurrentState().isGoal()) {
                //get the list of successors from the tempstate in tempnode
                ArrayList<State> tempSuccessors = tempNode.getCurrentState().genSuccessors();
                for (int i=0; i < tempSuccessors.size(); i++) {
                    SearchNode newNode = new SearchNode(tempNode, tempSuccessors.get(i));
                    if(!checkRepeats(newNode)) {
                        s.add(newNode);
                    }
                }
                searchCount++;
            } else { //FOUND WINNER!
                Stack<SearchNode> solutionPath = new Stack<SearchNode>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();
                while(tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);
                
                int loopSize = solutionPath.size();
                
                for(int i=0; i < loopSize; i++) {
                    tempNode = solutionPath.pop();
                    tempNode.getCurrentState().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The number of nodes examined: " + searchCount);
                System.exit(0);
            
            }
        }
        System.out.println("Error! no solution found!!");
    }
}