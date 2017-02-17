import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bbui1 on 2/16/2017.
 */
public class MancalaGameTests {
    MancalaGame game;
    Player p1, p2;
    Board board;

    public void setUpScenario1(){
        game = new MancalaGame();
        p1 = new Player(); p2 = new Player();
        board = new Board();
    }

    @Test
    public void getAndSetPlayerNames(){
        Player p = new Player();
        p.setName("Brandon");
        assertTrue(p.getName().equals("Brandon"));
    }

    // testing bidirectionality of players and game
    @Test
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
}