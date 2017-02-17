import java.util.LinkedList;
/**
 * Created by bbui1 on 2/16/2017.
 */

    // Collapse classes which are
    // associated with a 1 to 1 relationship into one class or consider upgrading the relationship to 0..1
    // to 0..1 or even 0..1 to n.

    // Upgrading relationship from 1 to 1 to 0..1 to 0..1
public class Board {
    private LinkedList<House> leftHouses = new LinkedList<House>();
    private LinkedList<House> rightHouses = new LinkedList<House>();
    private Store leftStore;
    private Store rightStore;
    private MancalaGame game;



}
