
import java.util.Arrays;
/**
 *
 * @author brad
 */
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
    }
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
    public GameState[] children() { //Creates children
        //GameState[] kids = new GameState[4]; //creates 4 length array
        GameState upstate = move_up();
        GameState downstate = move_down();
        GameState leftstate = move_left();
        GameState rightstate = move_right();
        GameState[] kids = {upstate, leftstate, downstate, rightstate};
        return kids;
    }
    @Override
    public String toString() {
        //System.out.println("TEST:" + Arrays.toString(valuesarray));
        return Arrays.toString(valuesarray); 
        /*StringBuilder mystring = new StringBuilder();
        for (int item : valuesarray) {
            mystring.append((char) item); //???
        }
        return mystring.toString();*/
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
            System.out.println("Up Not a Valid Move!");
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
            System.out.println("Down Not a Valid Move!");
            return null;
        }
        int[] array = swap(valuesarray, where_is_blank(), (where_is_blank()+3));
        GameState newstate = new GameState(this, array);
        return newstate;
    }
    public GameState move_left() { 
        //moves the 0 left
        if(!is_valid_move(2)) {// 2 is left
            System.out.println("Left Not a Valid Move!");
            return null;
        }
        int[] array = swap(valuesarray, where_is_blank(), (where_is_blank()-1));
        GameState newstate = new GameState(this, array);
        return newstate;
    }
    public GameState move_right() {
        //moves 0 to the right
        if (!is_valid_move(3)) { //3 is the right
            System.out.println("Right Not a Valid Move!");
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
                return !(where_is_blank() == 2 || where_is_blank() == 5 
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
}

