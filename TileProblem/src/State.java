import java.util.ArrayList;
/**
 *
 * @author brad
 */
public interface State {
    //Determine if state is the winning state
    boolean isGoal();
    
    //create a list of successors from the current state
    ArrayList<State> gen_children();
    
    //EXPERIMENTAL
    //Only creates children that are not already checked
    ArrayList<State> gen_children(ArrayList<State> checkedStates);
    
    
    //print the current state
    public void printState();
    
    // compare the actual state data
    public boolean equals(State s);
}
