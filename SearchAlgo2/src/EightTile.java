/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * USING NETBEANS 8.x
 */

/**
 *
 * @author brad
 */
import java.util.Stack; //to use built in stack for depth first search
import java.util.Arrays; //compare two arrays, inside of GameStates
import java.util.ArrayList; //create dynamic lists of objects, IE GameStates


public class EightTile { // 
    public static void main(String args[]) { //runs the program simulation
        System.out.println("In Main");
    }
    private ArrayList<GameState> children(GameState CurrentState) {
        //GameState initialstate = new GameState(); //create initial state
        ArrayList<GameState> kids = new ArrayList(); //Dynamic ArrayList
        //START CHECKING HERE
        return kids;
    }
    private GameState randomGameState(){
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
    public GameState(){} //default constructor
    public GameState(int valuesarray[]) {
        this.valuesarray = valuesarray;
    }
    public boolean is_valid() { //gives if this current state is a valid one
        // Java should protect us from doign illegal stuff....right?
        return true; //temp value
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
        //if(valuesarray[]) 
        return true;
    }
    public boolean in_middle() {
        return true;
    }
    public boolean in_side() {
        return true;
    }
}


