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
        /* We can only move up, down, left, or right. 
        * As such its easier to just check those 4 moves and see where the new
        * states take us.
        */
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
    public GameState(){} //default constructor
    public GameState(int valuesarray[]) {
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

