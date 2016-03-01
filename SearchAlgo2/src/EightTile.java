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
    public boolean itemexists(ArrayList<int[]> checkedvaluesarrays, int[] arraytofind) { //see if item is in arraylist
        for (int[] item : checkedvaluesarrays) {
            if(Arrays.equals(item, arraytofind)) return true;
        }
        /*for (int[] item : checkedvaluesarrays) {
            if(item == arraytofind) return true;
        }*/
        return false;
    }
    public boolean itemexists(ArrayList<String> checkedvaluesarray, String arraytofind) {
        for (String item : checkedvaluesarray) {
            if(item.equals(arraytofind)) return true;
        }
        return false;
    }
    public GameState depthsearch(GameState initialstate) {
        if(initialstate.is_win()) //not 100% needed
            return initialstate; 
        
        //ArrayList<GameState> exploredstates = new ArrayList(); //states already checked
        //ArrayList<int[]> explored_state_strings = new ArrayList<int[]>(); 
        ArrayList<String> explored_state_strings = new ArrayList<String>();
        Stack stack_of_states = new Stack(); //states to check
        
        stack_of_states.push(initialstate); //push initial state on stack
        ArrayList<GameState> childrenstates = new ArrayList(); //temporary list of states //STAR
        while (stack_of_states.size() > 0 ) {
            System.out.println("Stack:" + stack_of_states);
            //System.out.println("ExploredStates:" + explored_state_strings);
            GameState state = new GameState();
            state = (GameState) stack_of_states.pop();
            //System.out.println("TEST" + state.toString()); 
            //if (!explored_state_strings.(state.valuesarray)) { //FIGURE THIS OUT
            if(!itemexists(explored_state_strings, state.toString())) {
                System.out.println("IF?");
                if (state.is_win()) {
                    System.out.println("WINNER FOUND");
                    return state;
                } //Winning States
                explored_state_strings.add(state.toString()); // add to descovered
                childrenstates = children(state); //get the children of the state
                for (GameState kid : childrenstates) { //for each kid
                    System.out.println("Push Kid ontostack");
                    stack_of_states.push(kid); //push kid onto stack to check
                }
            } else {
                //System.out.println("Item " + state +" Not Found in stack");
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
