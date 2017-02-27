import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * author: Jess Miers
 * author: Brandon Bui
 * The MancalaGame class essentially builds Mancala game, setting up the left and right sides of the board
 * and their respective stores. The board is implemented as circular linked list with "across" connections for
 * easy access to the opposite and successor houses. The players are collected in a LinkedHashSet.
 * This implementation preserves the 1..n to association where a MancalaGame can have many houses while a house
 * may only be associated with 1 MancalaGame. Furthermore, a MancalaGame can have 0 or n Players while Players can have
 * 0 to 1 MancalaGame.
 * Note: our original class diagram depicted a Board class, however, seeing that Board and MancalaGame maintaned a
 * 1 to 1 relationship, Board has been collapsed into the MancalaGame class.
 */

public class MancalaGame {
    private Set<Player> players= new LinkedHashSet<Player>();
    private LinkedList<House> leftHouses = new LinkedList<House>();
    private LinkedList<House> rightHouses = new LinkedList<House>();
    private House leftStore;
    private House rightStore;

    //Constructor that calls the board setup method.
    //Client passes n number of houses they wish to play with. Essentially
    //being able to break the traditional Mancala setup of 6 left and 6 right houses. However,
    //a client cannot create an uneven amount of houses per side.
    public MancalaGame(int numHouses){
        makeBoard(numHouses);

    }
    //All of the board creation logic is done here
    //The circular linked list with across connections is created
    public void makeBoard(int numHouses){
       //Setup the stores first.
        //Stores are just Houses with 0 pebbles initially with a boolean value to determine that the house is indeed a store.
        leftStore = new House();
        leftStore.setPebbles(0);
        leftStore.setStore(true);
        rightStore = new House();
        rightStore.setPebbles(0);
        rightStore.setStore(true);

        //link up all the houses in one direction and init each house with 3 pebbles
        //assign a left store or a right store to the correct houses. This will make the
        //recursive redistribute function much easier to handle
        for(int i = 0; i < numHouses; i++){
            House lefthouse = new House();
            lefthouse.setPebbles(3);
            lefthouse.setStore(leftStore);
            House righthouse = new House();
            righthouse.setPebbles(3);
            righthouse.setStore(rightStore);
            leftHouses.add(lefthouse);
            rightHouses.add(righthouse);
        }
        //link in the correct, counterclockwise direction
        for(int i =0; i < numHouses-2; i++) {
            leftHouses.get(i).setNext(leftHouses.get(i + 1));
            rightHouses.get(i).setNext(rightHouses.get(i + 1));
        }

        //Complete the circle linking
        leftHouses.get(numHouses-1).setNext(leftStore);
        rightHouses.get(numHouses-1).setNext(rightStore);
        leftStore.setNext(rightHouses.get(0));
        rightStore.setNext(leftHouses.get(0));
        //link the houses that are across from eachother (preserves the opposite relationship)
        for(int i =0; i < numHouses; i++){
            leftHouses.get(i).setAcross(rightHouses.get((numHouses-i)-1));
            rightHouses.get(i).setAcross(leftHouses.get((numHouses-i)-1));
        }


    }//At this point, the board is setup successfully

    /**
     * Getters and Setters
     */
    public LinkedList<House> getLeftHouses(){
        return leftHouses;
    }
    public LinkedList<House> getRightHouses(){
        return rightHouses;
    }
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    /**
     * Add or Remove Players
     */
    public boolean addPlayer(Player p) {
        boolean addSuccess = players.add(p);
        if(addSuccess && p != null){
            p.setGame(this);
        }
        return addSuccess;
    }
    public boolean removePlayer(Player p){
        boolean changed = players.remove(p);
        if(changed && p != null){
            p.setGame(null);
        }
        return changed;
    }

    //TODO: Implement this function
    public boolean gameOver(){
        //check if one side of the board is empty
        //if it is return true or false if game is incomplete
        return false;
    }

}
