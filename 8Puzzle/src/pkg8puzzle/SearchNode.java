package pkg8puzzle;

/**
 *
 * @author brad
 */
public class SearchNode {
    private State currentState;
    private SearchNode parent;
    
    public SearchNode(SearchNode prev, State s) {
        currentstate = s;
        parent = prev;
    }
    public State getCurrentState() {
        return currentState;
    }
    public SearchNode getParent() {
        return parent;
    }
}
