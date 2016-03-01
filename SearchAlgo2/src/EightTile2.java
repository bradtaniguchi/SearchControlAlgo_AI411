
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

//SECOND CLEANER TEST IMPLIMENTATION 
/**
 *
 * @author brad
 */
public class EightTile2 {
    public static void main(String[] args) {
        System.out.println("In Main");
        int[] initialarray = {0,1,2,3,4,5,6,7,8};
        System.out.println("Array To Test " + Arrays.toString(initialarray));
        GameState initialstate = new GameState(null, initialarray);
        EightTile2 mytile = new EightTile2(); //run program
        GameState mysolution = mytile.depthsearch(initialstate);
    }
    public boolean iteminlist(ArrayList<String> explored, String tofind) {
        for(String item : explored) {
            if(item.equals(tofind)) return true;
        }
        return false;     
    }
    public boolean iteminlist(ArrayList<int[]> explored, int[] tofind) {
        for(int[] item : explored) {
            if(Arrays.equals(item, tofind)) return true;
        }
        return false;
    }
    public GameState depthsearch(GameState initialstate) {
        /*if(initialstate.is_win()) {
            System.out.println("Initial State Winning State!");
            return initialstate;
        }*/
        ArrayList<String> explored_states = new ArrayList<>();
        Stack<GameState> unchecked_states = new Stack<>();
        
        unchecked_states.push(initialstate);
        while(unchecked_states.size() > 0) {
            System.out.println("States: " + unchecked_states.toString());
            GameState state = new GameState();
            state = (GameState) unchecked_states.pop();
            if(!iteminlist(explored_states, Arrays.toString(state.valuesarray))) {
            //if(true) {
                if(state.is_win()) {
                    System.out.println("WINNER FOUND");
                    return state;
                }
                System.out.println("Adding State: " + state + "To Checked");
                explored_states.add(Arrays.toString(state.valuesarray));
                GameState[] childrenstates = state.children(); //get GameStateChildren
                for (GameState kid : childrenstates) {
                    if(kid != null) unchecked_states.push(kid);
                }
            }
            System.out.println("State:" + state + "Already Checked");
        }
        return null;
    }
    public boolean depthsearch(int[] initialstate) { //gives if winner exists
        ArrayList<int[]> explored_states = new ArrayList<>();
        Stack<int[]> unchecked_states = new Stack();
        
        unchecked_states.push(initialstate);
        
        while(unchecked_states.size() > 0) {
            ArrayList<int[]> kidstates = new ArrayList<>();
            int[] state = unchecked_states.pop(); 
            if(!iteminlist(explored_states, state)) {
                if(iswin(state)) {
                    return true; //there is a winner
                }
                explored_states.add(state);
                //kidstates = makekidstates(state);
                kidstates = makekidstates(state);
                for(int[] kid : kidstates){
                unchecked_states.push(kid);
                }
            }
        }
        System.out.println("No Win!");
        return false;   
    }
    public ArrayList<int[]> makekidstates(int[] currentarray) {
        ArrayList<int[]> returnarray = new ArrayList<>();
        if(is_valid_move(0, currentarray)) { //if its valid
            int[] uparray = move_up(currentarray);
            returnarray.add(uparray);
        }
        if(is_valid_move(1, currentarray)) {
            int[] downarray = move_down(currentarray);
            returnarray.add(downarray);
        }
        if(is_valid_move(2, currentarray)) {
            int[] leftarray = move_left(currentarray);
            returnarray.add(leftarray);
        }
        if(is_valid_move(2, currentarray)) {
            int[] rightarray = move_right(currentarray);
            returnarray.add(rightarray);
        }
        //int[][] returnarray = {uparray, leftarray, downarray, rightarray};
        return returnarray;
    }
    public boolean iswin(int[] array) {
        int[] winningarray = {1,2,3,8,0,4,7,6,5};
        return Arrays.equals(array, winningarray);
    }
    public int[] swap (int[] array, int a, int y) {
        if (a == y) return array; //wasted move
        int bin;
        bin = array[y];
        array[y] = array[a];
        array[a] = bin;
        
        return array;
    }
    public static int where_is_zero(int[] array) {
        //returns what spot in the array is "0" or blank
        for(int i=0;i < 9; i++) { //assumes array is length 9
            if (array[i] == 0) {
                return i;
            }
        }
        return -1; //somethings it WRONG
    }
    public boolean is_valid_move(int direction, int[] array) {
        /* 0 for up
        * 1 for down
        * 2 for left
        * 3 right
        * Does the hard work and checks to see if out of bounds, or legal
        */
        switch (direction) { //FOR ALL BELOW ADD LOGIC
            case 0: //move up
                return !(where_is_zero(array) == 0 
                        || where_is_zero(array) == 1 
                        || where_is_zero(array) == 2);
            case 1: //move down
                return !(where_is_zero(array) ==  6|| where_is_zero(array) == 7 
                        || where_is_zero(array) == 8);
            case 2: // move left
                return !(where_is_zero(array) == 0 || where_is_zero(array) == 3 
                        || where_is_zero(array) == 6);
            case 3: //move right
                return !(where_is_zero(array) == 2 || where_is_zero(array) == 5 
                        || where_is_zero(array) == 8);
            case -1:
                System.out.println("ERROR, Invalid blank position");
                return false;
            default:
                System.out.println("ERROR, Invalid Move Error");
                return false;
        }
    }
    public int[] move_up(int[] arraytomove) {
        if(!is_valid_move(0, arraytomove)) { //check outside first please
            return null;
        }
        int[] array = swap(arraytomove, where_is_zero(arraytomove), 
                where_is_zero(arraytomove)-3);
        return array;
    }
    public int[] move_down(int[] arraytomove) {
        if(!is_valid_move(1, arraytomove)) { //check outside first please
            return null;
        }
        int[] array = swap(arraytomove, where_is_zero(arraytomove),
                where_is_zero(arraytomove)+3);
        return array;
    }
    public int[] move_left(int[] arraytomove) {
        if(!is_valid_move(2, arraytomove)) { //check outside first please
            return null;
        }
        int[] array = swap(arraytomove, where_is_zero(arraytomove), 
                where_is_zero(arraytomove)-1);
        return array;
    }
    public int[] move_right(int[] arraytomove) {
        if(!is_valid_move(3, arraytomove)) { //check outside first please
            return null;
        }
        int[] array = swap(arraytomove, where_is_zero(arraytomove),
                where_is_zero(arraytomove)+1);
        return array;
    }
    
}
