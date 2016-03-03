import java.util.ArrayList;
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
    
    private int getHole() {
        int holeIndex = -1;
        for (int i=0; i < PUZZLE_SIZE; i++) {
            if(curBoard[i] == 'z') holeIndex=i;
        }
        return holeIndex;
    }
    private String copyBoard(char[] state) {
        String ret = new String(); //create new strings
        return null;
    }
    @Override
    public boolean isGoal() {
        return true;
    }
    @Override
    public ArrayList<State> gen_children() {
        return null;
    }
    @Override
    public ArrayList<State> gen_children(ArrayList<State> checkedStates) {
        return null;
    }
    @Override
    public void printState() {
        System.out.println("PrintState");
    }
    @Override
    public boolean equals(State s) {
        return true;
    }
    
    
}
