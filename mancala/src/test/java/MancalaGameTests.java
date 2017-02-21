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

    public void setUpScenario1(){
        game = new MancalaGame(6);  //testing a normal mancala board of 12 houses (6 per side)
        p1 = new Player(); p2 = new Player();
    }

    /**
     * Scenario: Brandon becomes a Player
     * 1. Player 1 is set to Brandon
     */
    @Test //Test the Player functionality
    public void getAndSetPlayerNames(){
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
    public void p1Andp2JoinGameThenBothLeave(){
        setUpScenario1();
        // game adds p1, p2 sets game, should do same thing
        game.addPlayer(p1);
        p2.setGame(game);

        assertTrue("game should contains these players",
                game.getPlayers().contains(p1) &&
                        game.getPlayers().contains(p2));
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
    @Test public void takeOppositePebblesTest(){
        setUpScenario1();
        p1.setName("Alex");
        p2.setName("Jess");
        game.addPlayer(p1);
        p2.setGame(game);
        House firstleft = game.getLeftHouses().get(0); //first left house
        int firstpebbles = firstleft.getStore().getPebbles();
        int acrosspebbles = firstleft.getAcross().getPebbles();
        firstleft.takeOppositePebbles();
        assertTrue("The opposite house should have 0 pebbles", firstleft.getAcross().getPebbles() == 0);
        assertTrue("Store should have opposite house + current pebbles", firstleft.getStore().getPebbles() == firstpebbles + acrosspebbles);

    }
    /**
     * Scenario: Alex redistributes his pebbles normally
     * 1. Alex and Jess enter the game
     * 2. Starting at his 0th house, Alex redistributes his pebbles counterclockwise
     * This scenario is similar to multiple scenarios from assignment 2.
     */
    @Test public void redistributePebblesTest(){
        setUpScenario1();
        p1.setName("Alex");
        p2.setName("Jess");
        game.addPlayer(p1);
        p2.setGame(game);
        House left = game.getLeftHouses().get(0);
        left.redistributeCounterClockwise();
        assertTrue("This house should have 0 pebbles", left.getPebbles() == 0);
        assertTrue("This house should have 4 pebbles", left.getNext().getPebbles() == 4);
        assertTrue("This house should have 4 pebbles", left.getNext().getNext().getPebbles() == 4);
        assertTrue("This house should have 4 pebbles", left.getNext().getNext().getNext().getPebbles() == 4);
        assertTrue("This house should have 4 pebbles", left.getNext().getNext().getNext().getNext().getPebbles() == 3);
        //obviously this is a silly way to write a unit test but given that it's just a test, efficiency is unnecessary
    }

}