import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author brad
 */
public class TileGameState implements State {
    /*NOTE state is defined with the following characters in a 7 length string
    * w - for white tile
    * b - for black tile
    * z - for space tile
    * ALSO NOTE using character arrays, instead of strings
    * for the VERY important reason that character arrays aren't immutable
    */
    private final int PUZZLE_SIZE = 7; //3 whites, 3 blacks, space
    private final char[][] GOALS= //7 possible goal states
    {{'w','w','w','b','b','b','z'},{'w','w','w','b','b','z','b'},
        {'w','w','w','b','z','b','b'},{'w','w','w','z','b','b','b'},
        {'w','w','z','w','b','b','b'},{'w','z','w','w','b','b','b'},
        {'z','w','w','w','b','b','b'}};
    
    private char[] curBoard; 
    
    public TileGameState(char[] board) { 
        curBoard = board;
    }
    private int getHole() {
        int holeIndex = -1;
        for (int i=0; i < PUZZLE_SIZE; i++) {
            if(curBoard[i] == 'z') holeIndex=i;
        }
        return holeIndex;
    }
    private char[] copyBoard(char[] state) {
        char[] ret = new char[PUZZLE_SIZE];
        for(int i=0; i<PUZZLE_SIZE ;i++) {
            ret[i] = state[i];
        }
        return ret;
    }
    private void swapAndStore(int d1, int d2, ArrayList<State> s) {
        char[] cpy = copyBoard(curBoard);
        char temp = cpy[d1];
        cpy[d1] = curBoard[d2];
        cpy[d2] = temp;
        s.add(new TileGameState(cpy));
    }
    @Override
    public boolean isGoal() { //QUESTIONABLE METHOD
        for(char[] goal : GOALS) {
            if(Arrays.equals(curBoard, goal)) return true;
        }
        return false;
    }
    @Override
    public ArrayList<State> gen_children() { //different name
        /*WARNING so many possibilities it might take forever
        *2 possible moves (from prompt)
        *A tile may move into the adjacent location for a cost of 1
        * A tile can hop over one or two tiles into the empty position
        * This has the cost equal to the number of tiles jumped over
        */
        ArrayList<State> successors = new ArrayList<State>();
        int hole = getHole();
        for (int i=0; i < PUZZLE_SIZE; i++) { //swap hole with each item
            swapAndStore(hole, i, successors); //create 7 states
        }
        return successors;
    }
    @Override
    public ArrayList<State> gen_children(ArrayList<State> checkedStates) {
        //WORK!
        ArrayList<State> successors = new ArrayList<State>();
        int hole = getHole();
        //if the state ISN'T in the checked states, check it out
        for(int i=0; i<PUZZLE_SIZE; i++) {
            swapAndStore(hole, i, successors); //create 7 states
        }
        return null;
    }
    private ArrayList<State> subtract(ArrayList<State> mylist, ArrayList<State> checkedStates) {
        /**
         * Checks checkedStates, with mylist, and returns a new cut down version
         * of mylist with duplicates removed
         * WORK IN PROGRESS
         */
        ArrayList<State> ret = new ArrayList<State>();
        /*for (State item : mylist) {//for each item in mylist
            
        }*/
        return null;// all states were already checked
    }
    @Override
    public void printState() { //NEED TO WORK ON
        System.out.println("PrintState");
        for (int i=0; i < PUZZLE_SIZE; i++) {
            System.out.print(" "+ curBoard[i] +" ");
        }
        System.out.println(""); //new line
    }
    @Override
    public boolean equals(State s) {
        if (Arrays.equals(curBoard, ((TileGameState) s).getCurBoard())) return true;
        return false; 
    }
    public char[] getCurBoard() {
        return curBoard;
    }
    
    
}
