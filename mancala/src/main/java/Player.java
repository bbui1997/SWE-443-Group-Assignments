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
    private int playerNumber = -1;
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
        if (name == null) throw new NullPointerException("player name cannot be null");
        this.name = name;
    }

    // Break the awkward recursion that comes in when you assign a player to a game and a game to a player
    // Can't have more than 2 players in game
    public void setGame(MancalaGame newGame) {
        if (this.game != newGame) {
            MancalaGame oldVal = this.game;
            this.game = newGame;
            if (oldVal != null) {
                oldVal.removePlayer(this);
            }
            if (this.game != null && !this.game.getPlayers().contains(this) && this.game.getPlayerCount() < 2) {
                this.game.addPlayer(this);
            }
        }
    }

    public MancalaGame getGame() {
        return this.game;
    }

    public void setPlayerNumber(int num) {
        if (num == 0 || num == 1) {
            playerNumber = num;
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }


    public boolean hasWon() {
        int myNum = getPlayerNumber();
        int otherNum = myNum ^ 1;
        if (this.getGame().canPlay())
            return false;

        if (getGame().getStoreCount(myNum) > getGame().getStoreCount(otherNum)) {
            return true;
        }

        return false;
    }
    //Added to check for ties
    public boolean hasTie(){

        int myNum = getPlayerNumber();
        int otherNum = myNum ^ 1;
        if (this.getGame().canPlay())
            return false;

        if (getGame().getStoreCount(myNum) == getGame().getStoreCount(otherNum)) {
            return true;
        }

        return false;
    }

}
