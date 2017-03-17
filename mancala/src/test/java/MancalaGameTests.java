import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author: Jess Miers
 * author: Brandon Bui
 * Test functionality and scenarios
 */
//Setup functionality to keep from duplicating code
//Each game needs board setup and two players
public class MancalaGameTests {
    MancalaGame game;
    Player p1, p2;
    House h1, h2, h3;

    public void setUpScenario1() {
        game = new MancalaGame(6);  //testing a normal mancala board of 12 houses (6 per side)
        p1 = new Player();
        p2 = new Player();
        p1.setName("Brandon");
        p2.setName("Sam");
    }

    public void setUpScenario2() {
        h1 = new House();
        h2 = new House();
        h3 = new House();
    }

    /**
     * Scenario: Brandon becomes a Player
     * 1. Player 1 is set to Brandon
     */
    @Test //Test the Player functionality
    public void getAndSetPlayerNames() {
        Player p = new Player();
        p.setName("Brandon");
        assertTrue(p.getName().equals("Brandon"));
    }

    /**
     * Scenario: Alex and Jess join the game, give up, and leave
     * 1. Alex and Jess enter the game
     * 2. Alex and Jess leave the game
     */
    @Test // Test the bidirectionality of players and game
    public void p1Andp2JoinGameThenBothLeave() {
        setUpScenario1();
        // game adds p1, p2 sets game, should do same thing
        game.addPlayer(p1);
        p2.setGame(game);
        assertTrue("game should contain p1", game.getPlayers().contains(p1));
        assertTrue("game should contain p2", game.getPlayers().contains(p2));
        assertTrue("players should know they are in the game",
                p1.getGame().equals(game) && p2.getGame().equals(game));

        // game removes p1, testing bidirectionality
        game.removePlayer(p1);
        assertTrue("game shouldn't contain player", !game.getPlayers().contains(p1));
        assertTrue("p1.game should be null", p1.getGame() == null);

        // p2 sets game to null, testing bidirectionality
        p2.setGame(null);
        assertTrue("game shouldn't contain player", !game.getPlayers().contains(p2));
        assertTrue("p2.game should be null", p2.getGame() == null);
    }

    /**
     * Scenario: Alex and Jess join the game and Alex gets to take pebbles from Jess's side
     * 1. Alex and Jess enter the game
     * 2. Alex decides to take Jess's pebbles opposite from the 0th house
     * 3. Alex adds those pebbles to his store.
     * This scenario is similar to Scenario 6 "Alex Legally and Successfully Wins the Game" except that no winner is
     * declared here as this is just a unit test.
     */
    @Test
    public void takeOppositePebblesTest() {
        setUpScenario1();
        p1.setName("Alex");
        p2.setName("Jess");
        game.addPlayer(p1);
        p2.setGame(game);
        game.getP1Houses().get(5).setPebbles(0);
        game.getP1Houses().get(2).redistributeCounterClockwise();
        assertTrue(game.getP1Houses().get(5).getPebbles() == 0 && game.getP2Houses().get(0).getPebbles() == 0);
        assertTrue(game.getP1Houses().get(0).getStore().getPebbles() == 4);
    }

    // Final pebble lands on an empty house
    // But the across house is also empty, so we don't take use takeOppositePebbles() method
    @Test
    public void DONTtakeOppositePebblesTest(){
        setUpScenario1();
        game.addPlayer(p1);
        p2.setGame(game);
        game.getP1Houses().get(5).setPebbles(0);
        game.getP2Houses().get(0).setPebbles(0);
        game.getP1Houses().get(2).redistributeCounterClockwise();
        assertTrue(game.getP1Houses().get(5).getPebbles() == 1);
        assertTrue(game.getP1Houses().get(0).getStore().getPebbles() == 0);
    }

    /**
     * Scenario: Alex redistributes his pebbles normally
     * 1. Alex and Jess enter the game
     * 2. Starting at his 0th house, Alex redistributes his pebbles counterclockwise
     * This scenario is similar to multiple scenarios from assignment 2.
     */
    @Test
    public void redistributePebblesTest() {
        setUpScenario1();
        p1.setName("Alex");
        p2.setName("Jess");
        game.addPlayer(p1);
        p2.setGame(game);
        House left = game.getP1Houses().get(0);
        left.redistributeCounterClockwise();
        assertTrue("This house should have 0 pebbles", left.getPebbles() == 0);
        assertTrue("This house should have 4 pebbles", left.getNext().getPebbles() == 4);
        assertTrue("This house should have 4 pebbles", left.getNext().getNext().getPebbles() == 4);
        assertTrue("This house should have 4 pebbles", left.getNext().getNext().getNext().getPebbles() == 4);
        assertTrue("This house should have 4 pebbles", left.getNext().getNext().getNext().getNext().getPebbles() == 3);
        //obviously this is a silly way to write a unit test but given that it's just a test, efficiency is unnecessary
    }

    // testing bidirectionality of setAcross
    @Test
    public void setAcrossHouseTest() {
        setUpScenario2();
        h3.setAcross(h1);
        h1.setAcross(h2);
        assertTrue("h3 across should be null", h3.getAcross() == null);
        assertTrue("h1 across should no longer null", h1.getAcross() != null);
        assertTrue("h1 across should be h2", h1.getAcross() == h2);
        // 1 set should test both
        assertTrue("h2 across is no longer null", h2.getAcross() != null);
        assertTrue("h2 across should be h1", h2.getAcross() == h1);
        // need to do set nulls
        h2.setAcross(null);
        assertTrue("setting one House's across as null makes the other null as well",
                h2.getAcross() == null && h1.getAcross() == null);
    }

    // testing bidirectionality of setNext/setPrev
    @Test
    public void setNextandPrevHouseTest() {
        setUpScenario2();
        h2.setPrev(h3);
        h1.setNext(h2);
        assertTrue("h3 next should now be null", h3.getNext() == null);
        assertTrue("h1 next should no longer be null", h1.getNext() != null);
        assertTrue("h1 next should be h2", h1.getNext() == h2);
        assertTrue("h2 prev should be h1", h2.getPrev() == h1);
        h2.setPrev(null);
        assertTrue(h1.getNext() == null && h2.getPrev() == null);
        h2.setPrev(h1);
        h1.setNext(null);
        assertTrue(h1.getNext() == null && h2.getPrev() == null);
    }

    // Test pairs, used to cover nearly identical code
    // Could refactor... but decide not to
    @Test
    public void p1HasWonAndHousesEmptyAndCheckStoreCount() {
        setUpScenario1();
        game = new MancalaGame(3);
        p1.setGame(game);
        p2.setGame(game);
        assertFalse(p1.hasWon());
        for (House h : game.getP2Houses()) {
            h.setPebbles(0); // manually force p2 to lose
        }
        game.getP1Houses().get(0).redistributeCounterClockwise();
        assertTrue(!game.canPlay());
        assertTrue(game.housesEmpty(game.getP2Houses()));
        assertTrue(game.getStoreCount(p1.getPlayerNumber()) == 9);
        assertTrue(p1.hasWon());

    }
    @Test
    public void p2HasWonAndHousesEmptyAndCheckStoreCount() {
        setUpScenario1();
        game = new MancalaGame(3);
        p1.setGame(game);
        p2.setGame(game);
        assertFalse(p2.hasWon());
        for (House h : game.getP1Houses()) {
            h.setPebbles(0); // manually force p1 to lose
        }
        game.getP2Houses().get(0).redistributeCounterClockwise();
        assertTrue(!game.canPlay());
        assertTrue(game.housesEmpty(game.getP1Houses()));
        assertTrue(game.getStoreCount(p2.getPlayerNumber()) == 9);
        assertTrue(p2.hasWon());

        // unsuccessful move
        assertFalse(game.getP2Houses().get(1).redistributeCounterClockwise());
    }
    @Test
    public void p1Andp2HaveTied(){
        setUpScenario1();
        p1.setGame(game);
        p2.setGame(game);
        for(int i = 0; i < game.getP1Houses().size(); i++){
            game.getP1Houses().get(i).setPebbles(0);
            game.getP2Houses().get(i).setPebbles(0);
        }
        assertTrue(!game.canPlay());
        assertTrue(game.getStoreCount(0) == game.getStoreCount(1));
        assertTrue(!p1.hasWon() && !p2.hasWon()); // they both tied, meaning they both haven't won
    }

    @Test
    public void getPlayerTurnTest(){
        setUpScenario1();
        assertTrue(game.getCurrentPlayer() == null); // no player joined yet
        p1.setGame(game);
        p2.setGame(game);
        // currently player 1 (0) turn
        assertTrue(game.getPlayerTurn() == 0);
        assertTrue(game.getCurrentTurn() == 0);
        assertTrue(game.getCurrentPlayer() == p1);
        game.getP1Houses().get(0).redistributeCounterClockwise();
        // they moved, now it is player 2 (1) turn
        assertTrue(game.getPlayerTurn() == 1);

        // it isn't player 1's turn anymore, so this move doesn't actually work
        game.getP1Houses().get(0).redistributeCounterClockwise();
        // it is still player 2's turn (1)
        assertTrue(game.getPlayerTurn() == 1);

        // testing that an invalid move won't change the player turn
        game.getP2Houses().get(0).setPebbles(0);
        game.getP2Houses().get(0).redistributeCounterClockwise(); // try to move on an empty house
        assertTrue(game.getPlayerTurn() == 1);
    }

    @Test
    public void attemptToAddMoreThan2PlayersThenRemoveP1AndAddP3(){
        setUpScenario1();
        Player p3 = new Player();
        p3.setName("Bob");
        game.addPlayer(p1); game.addPlayer(p2);
        game.addPlayer(p3);
        assertTrue(game.getPlayerCount() == 2);
        game.removePlayer(p1);
        game.addPlayer(p3);
        assertTrue(game.getPlayerCount() == 2);
        // p1 was Player 0, but now p3 took their spot. p3 is now effectively p1
        assertTrue(game.getCurrentPlayer() == p3);
    }

    @Test
    public void distributeOverOpponentStoreDoesntIncrementTheirCount(){
        setUpScenario1();
        game = new MancalaGame(3);
        p1.setGame(game);
        p2.setGame(game);
        game.getP1Houses().get(2).setPebbles(10);
        game.getP1Houses().get(2).redistributeCounterClockwise();
        assertTrue(game.getStoreCount(p2.getPlayerNumber()) == 0);
    }

    // Tests to make sure having the last pebble land in the store lets the player play again
    @Test
    public void lastPebbleLandsInStore(){
        setUpScenario1();
        p1.setGame(game);
        p2.setGame(game);
        game.getP1Houses().get(3).redistributeCounterClockwise();
        assertTrue(game.getStoreCount(p1.getPlayerNumber()) == 1);
        assertTrue(game.getCurrentTurn() == 0 && game.getPlayerTurn() == 0);
    }


    @Test (expected = IllegalArgumentException.class)
    public void getStoreTests(){
        setUpScenario1();
        game.getStoreCount(3); // invald argument, throws IllegalArgumentException
    }
    @Test (expected = IllegalStateException.class)
    public void attemptToTakeOppositeStore(){
        setUpScenario1();
        game.getP1Houses().get(0).getStore().takeOppositePebbles(); // can't take opposite from a store
    }


}