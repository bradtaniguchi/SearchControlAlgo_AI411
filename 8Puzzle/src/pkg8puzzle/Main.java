package pkg8puzzle;

/**
 *
 * @author brad
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] testarray = {0,2,3,1,8,4,7,6,5};//{0,1,2,3,4,5,6,7,8};
        DFSearch.search(testarray);
    }
    
}
