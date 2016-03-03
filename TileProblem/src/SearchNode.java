/*/**
 *
 * @author brad
 */
public class SearchNode {
    private State currentState;
    private SearchNode parent;
    
    public SearchNode(State s) {
        currentState = s;
        parent = null; //Root
    }
    public SearchNode(SearchNode prev, State s) { 
        currentState = s;
        parent = prev;
    }
    public State getCurrentState() {
        return currentState;
    }
    public SearchNode getParent() {
        return parent;
    }
}
