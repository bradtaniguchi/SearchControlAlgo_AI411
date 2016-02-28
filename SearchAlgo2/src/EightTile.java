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
    public static void main(String[] args) { //runs the program simulation
        System.out.println("In Main");
        int[] initialarray = {0,1,2,3,4,5,6,7,8};
        GameState initialstate = new GameState(null,initialarray); //test array
        EightTile mytile = new EightTile();
        GameState mysolution = mytile.depthsearch(initialstate);
        //mytile.printsolution(mysolution); //printout what we just solved
    }
    public GameState depthsearch(GameState initialstate) {
        if(initialstate.is_win()) //not 100% needed
            return initialstate; 
        
        //ArrayList<GameState> exploredstates = new ArrayList(); //states already checked
        ArrayList<int[]> explored_state_strings = new ArrayList(); 
        Stack stack_of_states = new Stack(); //states to check
        
        stack_of_states.push(initialstate); //push initial state on stack
        ArrayList<GameState> childrenstates = new ArrayList(); //temporary list of states
        while (stack_of_states.size() > 0 ) {
            System.out.println("Loops");
            GameState state = new GameState();
            state = (GameState) stack_of_states.pop();
            if (!explored_state_strings.contains(state.valuesarray)) { //FIGURE THIS OUT
                System.out.println("IF?");
                if (state.is_win()) {
                    System.out.println("WINNER FOUND");
                    return state;
                } //Winning States
                explored_state_strings.add(state.valuesarray); // add to descovered
                childrenstates = children(state); //get the children of the state
                for (GameState kid : childrenstates) { //for each kid
                    System.out.println("????");
                    stack_of_states.push(kid); //push kid onto stack to check
                }
            }
        }
    return null;
    }
    private ArrayList<GameState> children(GameState CurrentState) {
        //GameState initialstate = new GameState(); //create initial state
        System.out.println("CHILD");
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
            System.out.println("In here");
            kids.add(newstate);
        }
        newstate = CurrentState.move_right();
        if(newstate != null) {
            newstate.parent = CurrentState; //for path
            System.out.println("in here two");
            kids.add(newstate);
        }
        //kids.add(newstate); //NEEDED?
        return kids;
    }
    private void printsolution(GameState answer) {
        ArrayList<GameState> path = new ArrayList(); //create empty list
        while (answer.parent != null) {
            path.add(answer);
        }
        for (GameState item : path) {
            System.out.println("[ " + (String) item.valuesarray.toString() +" ]");
        }
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
        int[] array = valuesarray;
        array = swap(array, where_is_blank(), (where_is_blank()+1));
        //for(int i=0; i < 9; i++) System.out.print(valuesarray[i];
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
