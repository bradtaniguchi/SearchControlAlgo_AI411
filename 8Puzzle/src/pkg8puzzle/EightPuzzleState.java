package pkg8puzzle;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author brad
 */
public class EightPuzzleState implements State {
    private final int PUZZLE_SIZE = 9;
    
    private final int[] GOAL = new int[] //{1,2,3,4,5,6,7,8,0}; //is this right?
    {1,2,3,8,0,4,7,6,5}; //our version
    private int[] curBoard; 
    
    public EightPuzzleState(int[] board) {
        curBoard = board;
        
    }
    private int getHole() {
        int holeIndex =-1;
        for(int i=0; i< PUZZLE_SIZE; i++) {
            if(curBoard[i] == 0)
                holeIndex=i;
        }
        return holeIndex;
    }
    private int[] copyBoard(int[] state) {
        int[] ret = new int[PUZZLE_SIZE];
        for (int i=0;i< PUZZLE_SIZE; i++) {
            ret[i] = state[i];
        }
        return ret;
    }
    @Override
    public ArrayList<State> genSuccessors() {
        ArrayList<State> successors = new ArrayList<State>();
        int hole = getHole();
        
        if (hole != 0 && hole != 3 & hole != 6)
    }
            
}
