
/**
 * author: Jess Miers
 * author: Brandon Bui
 * The House class maintains all of the house/store properties of the Mancala Board. It is important to note
 * that our diagram once depicted a Store class, however, it is much more logical to collapse Store into a single
 * House class as Store and class maintained almost identical code and properties. Instead, a Store is now a House object
 * with the boolean condition isStore set to true. House also tracks a next and an across field in order to aid in the
 * circularly linked data structure of the board. All Houses contain n amount of pebbles. At init, a regular house contains
 * 3 pebbles while a store contains 0.
 */

public class House {
    private int pebbles;
    private boolean isStore = false;
    private House next;
    private House across;
    private House store;
    private MancalaGame game;
    private int playerNum; // 0 or 1
    /**
     * Getters and Setters
     */
    public void setStore(House house) {
        this.store = house;
    }

    public House getStore(House house) {
        return this.store;
    }

    public void setNext(House house) {
        this.next = house;
    }

    public void setAcross(House house) {
        this.across = house;
    }

    public House getNext() {
        return this.next;
    }

    public House getAcross() {
        return this.across;
    }

    public boolean getIsStore() {
        return this.isStore;
    }

    public House getStore() {
        return this.store;
    }

    public void setStore(boolean val) {
        this.isStore = val;
    }

    public int getPebbles() {
        return pebbles;
    }

    public void setPebbles(int num) {
        if (num < 0) throw new IllegalArgumentException("setting invalid amount pebbles");
        this.pebbles = num;
    }

    public void withGame(MancalaGame game) {
        this.game = game;
    }

    public MancalaGame getGame() {
        return this.game;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
    /**
     * take the pebbles from the opposite house and add them to the correct store
     */
    public void takeOppositePebbles() {
        //Check if a store house is calling this method
        //Stores cannot take opposite pebbles as there is no opposite house from a store
        //Throw an IllegalStateException in this case
        if (this.isStore) {
            throw new IllegalStateException("Cannot take opposite pebbles from a store");
        }
        //Grab the pebbles from the opposite store which is easily done given our circular data structure
        //Set the opposite house's pebbles to 0.
        else {
            this.store.setPebbles(this.across.getPebbles() + this.store.getPebbles());
            this.across.setPebbles(0);
        }

    }

    /**
     * Recursively redistribute the pebbles counter clockwise.
     */
    public void redistributeCounterClockwise() {
        //Start the recursion by passing the recursive method a pebble amount and a connected store
        //the store is important to pass so that a player does not redistribute pebbles into an opposite player's store
        if(!this.getIsStore() && this.getGame().canPlay() && this.getGame().getPlayerTurn() == this.getPlayerNum()) {
            this.next.redistributeCounterClockwiseRecurse(this.pebbles, this.store);
            this.pebbles = 0;
            this.getGame().incrementCurrentTurn();
        }
    }

    //Proceed with the recursive method
    //pebbles_in_hand is analogous to the pebbles a player would be holding as they distribute
    public void redistributeCounterClockwiseRecurse(int pebbles_in_hand, House store) {
        //If we have landed on a store that belongs to the opponent, we skip over it and redistribute to the next house
        if (this.getIsStore() && this != store) {
            this.next.redistributeCounterClockwiseRecurse(pebbles_in_hand, store);

        }   //Else, we distribute as normal
        if (pebbles_in_hand != 0) {
            this.pebbles += 1;
            this.next.redistributeCounterClockwiseRecurse(pebbles_in_hand - 1, store);
        }
    }

}

