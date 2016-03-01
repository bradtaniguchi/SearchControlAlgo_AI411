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
        //System.out.println("TEST");
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
        
        if (hole != 0 && hole != 3 & hole != 6) { //RIGHT?
            swapAndStore(hole - 1, hole, successors); //the magic!
        }
        if (hole != 6 && hole != 7 && hole != 8) { //UP?
            swapAndStore(hole + 3, hole, successors);
        }
        if (hole != 0 && hole != 1 && hole != 2) { //DOWN?
            swapAndStore(hole - 3, hole, successors);
        }
        if (hole != 2 && hole != 5 && hole != 8) { //LEFT?
            swapAndStore(hole + 1, hole, successors);
        }
        return successors;
            
    }
    private void swapAndStore(int d1, int d2, ArrayList<State> s) { //WAIT IS THE PASS BY REFERENCE?!?!?!
        int[] cpy = copyBoard(curBoard);
        int temp = cpy[d1];
        cpy[d1] = curBoard[d2];
        cpy[d2] = temp;
        s.add(new EightPuzzleState(cpy));
    }
    @Override
    public boolean isGoal() {
        if (Arrays.equals(curBoard, GOAL)) {
            return true;
        }
        return false;
    }
    @Override
    public void printState() {
        System.out.println(curBoard[0] + " | " + curBoard[1] + " | "
			+ curBoard[2]);
	System.out.println("---------");
	System.out.println(curBoard[3] + " | " + curBoard[4] + " | "
			+ curBoard[5]);
	System.out.println("---------");
	System.out.println(curBoard[6] + " | " + curBoard[7] + " | "
			+ curBoard[8]);

	}
    @Override
    public boolean equals(State s) {
        if (Arrays.equals(curBoard, ((EightPuzzleState) s).getCurBoard())){
            return true;
        }
        return false;
    }
    public int[] getCurBoard() {
        return curBoard;
    }
}
