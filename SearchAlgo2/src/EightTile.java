/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * USING NETBEANS 8.x
 */

/**
 *
 * @authors Bradley Taniguchi, Jarrett Dixon
 * 
 */
import java.util.Stack; //to use built in stack for depth first search
import java.util.Arrays; //compare two arrays, inside of GameStates
import java.util.ArrayList; //create dynamic lists of objects, IE GameStates

public class EightTile { // 
    public void main(String args[]) { //runs the program simulation
        System.out.println("In Main");
        int[] initialarray = {0,1,2,3,4,5,6,7,8};
        GameState initialstate = new GameState(null,initialarray); //test array
        GameState mysolution = depthsearch(initialstate);
        
    }
    public GameState bredthsearch() { //REMOVE SOON
        int[] initialarray = {0,1,2,3,4,5,6,7,8};
        GameState initialstate = new GameState(null,initialarray); //test array
        ArrayList<GameState> exploredstates = new ArrayList(); //states already checked
        Stack stack_of_states = new Stack(); //states to check
        if (initialstate.is_win()) return initialstate; //that was easy
        stack_of_states.add(initialstate); //
        while (stack_of_states.size() > 0) { //while stack has stuff in it to check
            GameState State = new GameState();
            State = (GameState) stack_of_states.pop(); //check the state at the top of the stack
            if(State.is_win()) return State; //found a winner
            exploredstates.add(State); //add we explored this state, its not a winner
            ArrayList<GameState> kidsofstates = new ArrayList(); //get the kids of the current State
            for (GameState kid : kidsofstates) { //for each kid in the list
                
            }
            
        }
        //children(initialstate);
        return null;
    }
    public GameState depthsearch(GameState initialstate) {
    /*    1  procedure DFS-iterative(G,v): //FROM WIKIPEDIA
2      let S be a stack
3      S.push(v)
4      while S is not empty
5            v = S.pop()
6            if v is not labeled as discovered:
7                label v as discovered
8                for all edges from v to w in G.adjacentEdges(v) do
9                    S.push(w)
    */
        if(initialstate.is_win())
            return initialstate; 
        ArrayList<GameState> exploredstates = new ArrayList(); //states already checked
        Stack stack_of_states = new Stack(); //states to check
        ArrayList<GameState> childrenstates = new ArrayList(); //temporary list of states
        while (stack_of_states.size() > 0 ) {
            GameState state = new GameState();
            state = (GameState) stack_of_states.pop();
            if (!exploredstates.contains(state)) { //DEBUG THIS, not found yet
                if (state.is_win()) return state; //Winning States
                exploredstates.add(state); // add to descovered
                childrenstates = children(state); //get the children of the state
                for (GameState kid : childrenstates) { //for each kid
                    stack_of_states.push(kid); //push kid onto stack to check
                }
            }
        
        }
    return null;
    }
    private ArrayList<GameState> children(GameState CurrentState) {
        //GameState initialstate = new GameState(); //create initial state
        ArrayList<GameState> kids = new ArrayList(); //Dynamic ArrayList
        /* We can only move up, down, left, or right. 
        * As such its easier to just check those 4 moves and see where the new
        * states take us.
        */
        GameState newstate = CurrentState.move_up(); //create new state up
        if (newstate != null) {
            newstate.parent = CurrentState; //to get winning path
            kids.add(newstate); //add newstate to List
        }
        newstate = CurrentState.move_left(); //do I need new??
        if (newstate != null) {
            newstate.parent = CurrentState; //for path
            kids.add(newstate);
        }
        newstate = CurrentState.move_down();
        if(newstate != null) {
            newstate.parent = CurrentState; //for path
            kids.add(newstate);
        }
        newstate = CurrentState.move_right();
        if(newstate != null) {
            newstate.parent = CurrentState; //for path
            kids.add(newstate);
        }
        //kids.add(newstate); //NEEDED?
        return kids;
    }
    private GameState randomGameState() {
        return null;
    }
}
class GameState { //only useful in this local file
    /*basic array: [0,1,2,3,4,5,6,7,8]
    * [0,1,2] is this the correct layout?
    * [3,4,5]
    * [6,7,8]
    */
    public int[] valuesarray = new int[9]; //holds the values for each square
    public GameState parent = null; 
    public GameState() {}
    public GameState(GameState parent){
        this.parent = parent;
    } //default constructor
    public GameState(GameState parent, int valuesarray[]) {
        this.parent = parent;
        this.valuesarray = valuesarray;
    }
    private int where_is_blank() {
        //returns what spot in the array is "0" or blank
        for(int i=0;i < 9; i++) {
            if (valuesarray[i] == 0) {
                return i;
            }
        }
        return -1; //somethings it WRONG
    }
    private int[] swap(int[] array,int a, int y) {// swaps the two given indices
        if (a == y) return null; //wasted MOVE
        int bin;
        bin = array[y];
        array[y] = array[a];
        array[a] = bin;
        
        return array;
    }
    public GameState move_up() {// returns gamestate after move
        //moves the 0 up
        if (!is_valid_move(0)) { // 0 is up
            System.out.println("Not a Valid Move!");
            return null;
        } //if not valid move up, don't do it
        //NOTE subtracting position is valid
        int[] array = swap(valuesarray,where_is_blank(),(where_is_blank()-3));
        GameState newstate = new GameState(this, array);
        return newstate;
    }
    public GameState move_down() {
        //moves the 0 down
        if (!is_valid_move(1)) {// 1 is down
            System.out.println("Not a Valid Move!");
            return null;
        }
        int[] array = swap(valuesarray, where_is_blank(), (where_is_blank()+3));
        GameState newstate = new GameState(this, array);
        return newstate;
    }
    public GameState move_left() { 
        //moves the 0 left
        if(!is_valid_move(2)) {// 2 is left
            System.out.println("Not a Valid Move!");
            return null;
        }
        int[] array = swap(valuesarray, where_is_blank(), (where_is_blank()-1));
        GameState newstate = new GameState(this, array);
        return newstate;
    }
    public GameState move_right() {
        //moves 0 to the right
        if (!is_valid_move(3)) { //3 is the right
            System.out.println("Not a Valid Move!");
            return null;
        }
        int[] array = swap(valuesarray, where_is_blank(), (where_is_blank()+1));
        GameState newstate = new GameState(this, array);
        return newstate;
    }
    public boolean is_valid_move(int direction) { 
        /* 0 for up
        * 1 for down
        * 2 for left
        * 3 right
        * Does the hard work and checks to see if out of bounds, or legal
        */
        switch (direction) { //FOR ALL BELOW ADD LOGIC
            case 0: //move up
                return !(where_is_blank() == 0 || where_is_blank() == 1 
                        || where_is_blank() == 2);
            case 1: //move down
                return !(where_is_blank() ==  6|| where_is_blank() == 7 
                        || where_is_blank() == 8);
            case 2: // move left
                return !(where_is_blank() == 0 || where_is_blank() == 3 
                        || where_is_blank() == 6);
            case 3: //move right
                return !(where_is_blank() == 6 || where_is_blank() == 7 
                        || where_is_blank() == 8);
            case -1:
                System.out.println("ERROR, Invalid blank position");
                return false;
            default:
                System.out.println("ERROR, Invalid Move Error");
                return false;
        }
    }
    public boolean is_win() { //is this a winning state of the game?
        int[] winningarray = {1,2,3,8,0,4,7,6,5};
        if (Arrays.equals(valuesarray, winningarray)) {  //[1,2,3,8,0,4,7,6,5]:
            return true; //temp value
        } else {
            return false;
        }
    }
    public boolean in_corner() { //WORK BELOW
        if (valuesarray[0] == 0|| valuesarray[2] == 0 || valuesarray[6] == 0
                || valuesarray[8] == 0) {
            return true;
        } else 
            return false;
    }
    public boolean in_middle() {
        if (valuesarray[4] == 0) return true;
        else return false;
    }
    public boolean in_side() {
        if(valuesarray[1] == 0|| valuesarray[3] == 0 
                || valuesarray[5] == 0 || valuesarray[7] == 0) {
            return true;
        } else
            return true;
    }
}


