import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * author: Jess Miers
 * author: Brandon Bui
 * The Player class tracks the actual players in the MancalaGame. The Player will have a name and the MancalaGame they are
 * associated with, thus preserving the many players to one MancalaGame but one MancalaGame to many players relationship.
 * To be competed later:
 * There needs to be logic added to connect a player and their store and their side of the board. This is unnecessary for
 * the completion of this assignment but something to think about when we need to determine a winner of the game.
 */
public class Player {
    private String name;
    private MancalaGame game;

    //TODO: FIGURE OUT HOW TO CONNECT A PLAYER TO A STORE AND THEIR OWN SIDE OF THE BOARD. Might need these...
    // private House store;          To be implemented later
    //private LinkedList<House> myhouses;  To be implemented later



    /**
     * Getters and Setters
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null) throw new NullPointerException("player name cannot be null");
        this.name = name;
    }
    //Break the awkward recursion that comes in when you assign a player to a game and a game to a player
    public void setGame(MancalaGame newGame){
        if(this.game != newGame){
            MancalaGame oldVal = this.game;
            this.game = newGame;
            if(oldVal != null){
                oldVal.removePlayer(this);
            }
            if(this.getGame() != null){
                this.getGame().addPlayer(this);
            }
        }
    }
    public MancalaGame getGame(){ return this.game; }

    //TODO: Implement this function
    public boolean haveWon(){
        //make a call to gameOver() in MancalaGame to check that the game actually ended
        //check if you have more pebbles than you're opponent.
            //return true if so. false if not
        //make sure to check for ties. Ties occur when the game is over but there are the same amount of pebbles in each store
        return false;
    }


}
