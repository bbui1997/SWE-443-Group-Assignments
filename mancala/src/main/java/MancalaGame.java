import java.lang.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * author: Jess Miers
 * author: Brandon Bui
 * The MancalaGame class essentially builds Mancala game, setting up the left and right sides of the board
 * and their respective stores. The board is implemented as circular linked list with "across" connections for
 * easy access to the opposite and successor houses. The players are collected in a LinkedHashSet.
 * This implementation preserves the 1..n to association where a MancalaGame can have many houses while a house
 * may only be associated with 1 MancalaGame. Furthermore, a MancalaGame can have 0 or n Players while Players can have
 * 0 to 1 MancalaGame.
 * Note: our original class diagram depicted a Board class, however, seeing that Board and MancalaGame maintained a
 * 1 to 1 relationship, Board has been collapsed into the MancalaGame class.
 */

public class MancalaGame {
    private ArrayList<Player> players = new ArrayList<Player>();
    private LinkedList<House> p1Houses = new LinkedList<House>();
    private LinkedList<House> p2Houses = new LinkedList<House>();
    private House p1Store;
    private House p2Store;
    private int playerTurn;
    private int currentTurn;

    //Constructor that calls the board setup method.
    //Client passes n number of houses they wish to play with. Essentially
    //being able to break the traditional Mancala setup of 6 left and 6 right houses. However,
    //a client cannot create an uneven amount of houses per side.
    public MancalaGame(int numHouses) {
        makeBoard(numHouses);

    }

    //All of the board creation logic is done here
    //The circular linked list with across connections is created
    public void makeBoard(int numHouses) {
        //Setup the stores first.
        //Stores are just Houses with 0 pebbles initially with a boolean value to determine that the house is indeed a store.
        p1Store = new House();
        p1Store.withGame(this);
        p1Store.setPebbles(0);
        p1Store.setStore(true);
        p2Store = new House();
        p2Store.withGame(this);
        p2Store.setPebbles(0);
        p2Store.setStore(true);

        //link up all the houses in one direction and init each house with 3 pebbles
        //assign a left store or a right store to the correct houses. This will make the
        //recursive redistribute function much easier to handle
        for (int i = 0; i < numHouses; i++) {
            House p1house = new House();
            p1house.withGame(this);
            p1house.setPebbles(3);
            p1house.setStore(p1Store);
            p1house.setPlayerNum(0);
            House p2house = new House();
            p2house.withGame(this);
            p2house.setPebbles(3);
            p2house.setStore(p2Store);
            p2house.setPlayerNum(1);
            p1Houses.add(p1house);
            p2Houses.add(p2house);
        }
        //link in the correct, counterclockwise direction
        for (int i = 0; i < numHouses - 2; i++) {
            p1Houses.get(i).setNext(p1Houses.get(i + 1));
            p2Houses.get(i).setNext(p2Houses.get(i + 1));
        }

        //Complete the circle linking
        p1Houses.get(numHouses - 1).setNext(p1Store);
        p2Houses.get(numHouses - 1).setNext(p2Store);
        p1Store.setNext(p2Houses.get(0));
        p2Store.setNext(p1Houses.get(0));
        //link the houses that are across from each other (preserves the opposite relationship)
        for (int i = 0; i < numHouses; i++) {
            p1Houses.get(i).setAcross(p2Houses.get((numHouses - i) - 1));
            p2Houses.get(i).setAcross(p1Houses.get((numHouses - i) - 1));
        }
        playerTurn = 0;
        currentTurn = 0;
    }//At this point, the board is setup successfully

    /**
     * Getters and Setters
     */
    public List<House> getP1Houses() {
        return Collections.unmodifiableList(p1Houses);
    }

    public List<House> getP2Houses() {
        return Collections.unmodifiableList(p2Houses);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean housesEmpty(List<House> house) {
        for (House h : house) {
            if (h.getPebbles() != 0)
                return false;
        }
        return true;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getPlayerTurn() {
        return currentTurn % 2;
    }

    public Player getCurrentPlayer() {
        for (int i = 0; i < getPlayerCount(); i++) {
            if (players.get(i).getPlayerNumber() == getPlayerTurn()) {
                return players.get(i);
            }
        }
        return null;
    }

    public void incrementCurrentTurn() {
        currentTurn++;
    }

    public int getStoreCount(int playerNum) {
        if (playerNum == 0) return p1Store.getPebbles();
        if (playerNum == 1) return p2Store.getPebbles();
        else {
            throw new IllegalArgumentException("illegal argument: playerNum, getStoreCount()");
        }
    }

    /**
     * Add or Remove Players
     */
    public boolean addPlayer(Player p) {
        boolean addSuccess = false;
        if (getPlayerCount() >= 2) {
            return false;
        }
        if (!players.contains(p) && p != null) {
            addSuccess = players.add(p);
            p.setGame(this);
            if (getPlayerCount() == 1) {
                p.setPlayerNumber(0);
            } else {
                if (players.get(0).getPlayerNumber() == 0) {
                    p.setPlayerNumber(1);
                } else {
                    p.setPlayerNumber(0);
                }
            }
        }
        return addSuccess;
    }

    public boolean removePlayer(Player p) {
        boolean changed = players.remove(p);
        if (changed && p != null) {
            p.setGame(null);
        }
        return changed;
    }


    //TODO: Implement this function
    public boolean canPlay() {
        //check if one side of the board is empty
        //if it is return true -- false if game is incomplete
        return !housesEmpty(getP1Houses()) || !housesEmpty(getP2Houses());
    }

}
