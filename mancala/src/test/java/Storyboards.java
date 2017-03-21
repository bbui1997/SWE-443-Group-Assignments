/**
 * Created by Spade on 3/18/17.
 */

import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;


public class Storyboards {
      /**
    * 
    * @see <a href='../../../../doc/Scenario1.html'>Scenario1.html</a>
 */
   @Test
    public void testScenario1(){
       Storyboard storyboard = new Storyboard();

       storyboard.add("1. (initial setup) Jess and Alex sit down to play mancala with 0 stones in their store");

       storyboard.markCodeStart();
       MancalaGame mancalaGame  = new MancalaGame(6);

       Player jess = new Player();
       jess.setName("Jess");
       jess.setGame(mancalaGame);

       Player alex = new Player();
       alex.setName("Alex");
       alex.setGame(mancalaGame);

       storyboard.addCode();

       storyboard.addObjectDiagram(
           "scenario1",mancalaGame,
           "jess",jess,
           "alex",alex
       );

       storyboard.assertEquals("Jess' store: ",0,mancalaGame.getStoreCount(jess.getPlayerNumber()));
       storyboard.assertEquals("Alex's store: ",0,mancalaGame.getStoreCount(alex.getPlayerNumber()));

       //========================================================

       storyboard.add("2. Jess grabs all 36 pebbles and places them into her store");
       storyboard.markCodeStart();

       for (House h : mancalaGame.getP2Houses()) {
           h.setPebbles(0);
       }

       for (House h : mancalaGame.getP1Houses()){
           h.setPebbles(0);
       }

       mancalaGame.getP1Houses().get(0).getStore().setPebbles(36);
       storyboard.addCode();

       storyboard.addObjectDiagramOnlyWith(mancalaGame,jess);

       storyboard.assertEquals("Jess' store: ",36,mancalaGame.getStoreCount(jess.getPlayerNumber()));
       storyboard.assertEquals("Alex's store: ",0,mancalaGame.getStoreCount(alex.getPlayerNumber()));

       storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../../doc/Scenario2.html'>Scenario2.html</a>
 */
   @Test
    public void testScenario2(){
        Storyboard storyboard = new Storyboard();

        storyboard.add("1.Kevin and Jess play Mancala. "
                + "Kevin sets up the Mancala board game correctly: 3 pebbles per house");
        storyboard.markCodeStart();

        MancalaGame game = new MancalaGame(6);

        Player kevin = new Player();
        kevin.setName("Kevin");
        kevin.setGame(game);

        Player jess = new Player();
        jess.setName("Jess");
        jess.setGame(game);

        storyboard.addCode();
        storyboard.addObjectDiagram(
                "scenario2",game,
                "Kevin",kevin,
                "Jes",jess);

        storyboard.assertEquals("Kevin's Store: ",0,game.getStoreCount(kevin.getPlayerNumber()));
        storyboard.assertEquals("Jess' Store",0,game.getStoreCount(jess.getPlayerNumber()));

       //====================================================

        storyboard.add("2.Kevin takes a turn trying to avoid giving Jess points");
        storyboard.markCodeStart();
        House choice = game.getP1Houses().get(0);
        choice.redistributeCounterClockwise();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,game.getP1Houses(),game.getP2Houses(),kevin,jess);

        storyboard.assertEquals("Pebbles in House 0:",0,game.getP1Houses().get(0).getPebbles());

        for(int i=1;i<4;i++){
           House h = game.getP1Houses().get(i);
           storyboard.assertEquals("Pebbles in Kevin's House "+i+":",4,h.getPebbles());
        }

        storyboard.assertEquals("Kevin's Store:",0,game.getStoreCount(kevin.getPlayerNumber()));
        storyboard.assertEquals("Jess' Store:",0,game.getStoreCount(jess.getPlayerNumber()));

        //===================================================

        storyboard.add("3.Jess takes a turn trying to avoid giving Kevin points");
        storyboard.markCodeStart();
        House jessChoice = game.getP2Houses().get(0);
        jessChoice.redistributeCounterClockwise();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,game.getP1Houses(),game.getP2Houses(),kevin,jess);

        for(int i=1;i<4;i++){
           House h = game.getP2Houses().get(i);
           storyboard.assertEquals("Pebbles in Jess' House "+i+":",4,h.getPebbles());
        }

        storyboard.assertEquals("Kevin's Store:",0,game.getStoreCount(kevin.getPlayerNumber()));
        storyboard.assertEquals("Jess' Store:",0,game.getStoreCount(jess.getPlayerNumber()));

        //======================================================

        storyboard.add("4.Both Kevin and Jess quit the game.");
        storyboard.markCodeStart();

        game.removePlayer(kevin);
        game.removePlayer(jess);

        storyboard.addCode();
        storyboard.addObjectDiagram(game);

        storyboard.assertTrue("All players left game:",game.getPlayers().isEmpty());
        storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../../doc/Scenario3.html'>Scenario3.html</a>
 */
   @Test
    public void testScenario3(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("1.(Initial Setup) Jeff and Jess play Mancala until the board is empty.");
        storyboard.markCodeStart();

        MancalaGame game = new MancalaGame(6);

        Player jeff = new Player();
        jeff.setName("Jeff");
        jeff.setGame(game);

        Player jess = new Player();
        jess.setName("Jess");
        jess.setGame(game);

        storyboard.addCode();
        storyboard.addObjectDiagram(
                "scenario3",game,
                "Jeff",jeff,
                "Jess",jess);
        storyboard.assertTrue("Jeff joins game:",game.getPlayers().contains(jeff));
        storyboard.assertTrue("Jess joins game:",game.getPlayers().contains(jess));

        //==================================================
        storyboard.add("2.It's Jeff's turn and the state of his houses are 0,1,1,1,0,0" +
                "Jeff has 16 stones in his store.");
        storyboard.markCodeStart();
        game.setCurrentTurn(jeff.getPlayerNumber());

        game.getP1Houses().get(0).setPebbles(0);
        game.getP1Houses().get(1).setPebbles(0);
        game.getP1Houses().get(2).setPebbles(1);
        game.getP1Houses().get(3).setPebbles(1);
        game.getP1Houses().get(4).setPebbles(1);
        game.getP1Houses().get(5).setPebbles(0);

        House store = game.getP1Houses().get(0).getStore();
        store.setPebbles(16);
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jeff);

        storyboard.assertEquals("Jeff's Store: ",16,game.getStoreCount(jeff.getPlayerNumber()));
        storyboard.assertEquals("Jeff's House 1: ",0,game.getP1Houses().get(0).getPebbles());
        storyboard.assertEquals("Jeff's House 2: ",0,game.getP1Houses().get(1).getPebbles());
        storyboard.assertEquals("Jeff's House 3: ",1,game.getP1Houses().get(2).getPebbles());
        storyboard.assertEquals("Jeff's House 4: ",1,game.getP1Houses().get(3).getPebbles());
        storyboard.assertEquals("Jeff's House 5: ",1,game.getP1Houses().get(4).getPebbles());
        storyboard.assertEquals("Jeff's House 6: ",0,game.getP1Houses().get(5).getPebbles());
        //==================================================
        storyboard.add("3.It's Jess's turn and the state of her houses are 0,0,0,0,2,1. " +
                "Her store contains 14 stones.");
        storyboard.markCodeStart();
        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP2Houses().get(0).setPebbles(0);
        game.getP2Houses().get(1).setPebbles(0);
        game.getP2Houses().get(2).setPebbles(0);
        game.getP2Houses().get(3).setPebbles(0);
        game.getP2Houses().get(4).setPebbles(2);
        game.getP2Houses().get(5).setPebbles(1);

        House p2Store = game.getP2Houses().get(0).getStore();
        p2Store.setPebbles(14);
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's Store: ",14,game.getStoreCount(jess.getPlayerNumber()));
        storyboard.assertEquals("Jess's House 1: ",0,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2: ",0,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3: ",0,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4: ",0,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5: ",2,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6: ",1,game.getP2Houses().get(5).getPebbles());

        //==================================================
        storyboard.add("4.It is Jess's turn. She starts with the house with 2 pebbles and distributes them" +
                "counter clockwise.");
        storyboard.markCodeStart();
        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP2Houses().get(4).redistributeCounterClockwise();
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 5: ",0,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6: ",2,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store:",15,game.getStoreCount(jess.getPlayerNumber()));

        //==================================================
        storyboard.add("5.It is Jess's turn again. She plays until she clears her side of the board.");
        storyboard.markCodeStart();
        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP2Houses().get(5).redistributeCounterClockwise();
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 1: ",0,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2: ",0,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3: ",0,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4: ",0,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5: ",0,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6: ",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store:",16,game.getStoreCount(jess.getPlayerNumber()));
        //==================================================
        storyboard.add("6.Jeff incorrectly wins the game with 20 pebbles in his store.");
        storyboard.markCodeStart();

        game.getP1Houses().get(0).setPebbles(0);
        game.getP1Houses().get(1).setPebbles(0);
        game.getP1Houses().get(2).setPebbles(0);
        game.getP1Houses().get(3).setPebbles(0);
        game.getP1Houses().get(4).setPebbles(0);
        game.getP1Houses().get(5).setPebbles(0);
        game.getP1Houses().get(0).getStore().setPebbles(20);

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jeff);
        storyboard.assertEquals("Jeff's House 1: ",0,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Jeff's House 2: ",0,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Jeff's House 3: ",0,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Jeff's House 4: ",0,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Jeff's House 5: ",0,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Jeff's House 6: ",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Jeff's Store:",20,game.getStoreCount(jeff.getPlayerNumber()));
        storyboard.assertTrue("Jeff wins",game.getStoreCount(jeff.getPlayerNumber())>game.getStoreCount(jess.getPlayerNumber()));
        storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../../doc/Scenario4.html'>Scenario4.html</a>
 */
   @Test
    public void testScenario4(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("1.Alex and Jess setup the board to play.");
        storyboard.markCodeStart();

        MancalaGame game = new MancalaGame(6);

        Player jess = new Player();
        jess.setName("Jess");
        jess.setGame(game);

        Player alex =  new Player();
        alex.setName("Alex");
        alex.setGame(game);

        storyboard.addCode();
        storyboard.addObjectDiagram(game,alex,jess);
        storyboard.assertTrue("Jess is a player",game.getPlayers().contains(jess));
        storyboard.assertTrue("Alex is a player",game.getPlayers().contains(alex));

        //=====================================================

        storyboard.add("2.Alex’s current board state is 0, 1, 1, 1, 0, 0 and his house contains 15 pebbles.");
        storyboard.markCodeStart();

        //game.setCurrentTurn(alex.getPlayerNumber());

        game.getP2Houses().get(0).setPebbles(0);
        game.getP2Houses().get(1).setPebbles(1);
        game.getP2Houses().get(2).setPebbles(1);
        game.getP2Houses().get(3).setPebbles(1);
        game.getP2Houses().get(4).setPebbles(0);
        game.getP2Houses().get(5).setPebbles(0);

        House p2Store = game.getP2Houses().get(0).getStore();
        p2Store.setPebbles(15);

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,alex);

        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",1,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",1,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",1,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Alex's Store: ",15,game.getStoreCount(alex.getPlayerNumber()));

        //======================================================

        storyboard.add("3.Jess’s current board state is 0, 0,0,0,2,1 and her house contains 15 pebbles.");
        storyboard.markCodeStart();

        //game.setCurrentTurn(jess.getPlayerNumber());

        game.getP1Houses().get(0).setPebbles(0);
        game.getP1Houses().get(1).setPebbles(0);
        game.getP1Houses().get(2).setPebbles(0);
        game.getP1Houses().get(3).setPebbles(0);
        game.getP1Houses().get(4).setPebbles(2);
        game.getP1Houses().get(5).setPebbles(1);

        House p1Store = game.getP1Houses().get(0).getStore();
        p1Store.setPebbles(15);

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 1: ",0,game.getP1Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2: ",0,game.getP1Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3: ",0,game.getP1Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4: ",0,game.getP1Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5: ",2,game.getP1Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6: ",1,game.getP1Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store: ",15,game.getStoreCount(jess.getPlayerNumber()));

        //=====================================================
        storyboard.add("It is Jess's turn. She moves pebbles from house 5 and places one in house 6 and her store.");
        storyboard.markCodeStart();

        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP1Houses().get(4).redistributeCounterClockwise();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 5:",0,game.getP1Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6:",2,game.getP1Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store:",16,game.getStoreCount(jess.getPlayerNumber()));

        //=====================================================
        storyboard.add("It is Jess's turn. She moves her last remaining pebbles from house 6 to her store" +
                "and Alex's house and wins the game.");
        storyboard.markCodeStart();

        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP1Houses().get(5).redistributeCounterClockwise();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 1: ",0,game.getP1Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2: ",0,game.getP1Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3: ",0,game.getP1Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4: ",0,game.getP1Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5: ",0,game.getP1Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6: ",0,game.getP1Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store:",17,game.getStoreCount(jess.getPlayerNumber()));

        storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../../doc/Scenario5.html'>Scenario5.html</a>
 */
   @Test
    public void testScenario5(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("1.Alex and Jess setup the board to play.");
        storyboard.markCodeStart();

        MancalaGame game = new MancalaGame(6);

        Player jess = new Player();
        jess.setName("Jess");
        jess.setGame(game);

        Player alex =  new Player();
        alex.setName("Alex");
        alex.setGame(game);

        storyboard.addCode();
        storyboard.addObjectDiagram(game,alex,jess);
        storyboard.assertTrue("Jess is a player",game.getPlayers().contains(jess));
        storyboard.assertTrue("Alex is a player",game.getPlayers().contains(alex));

        //=====================================================

        storyboard.add("2.It's Alex's turn. Alex redistributes his first 3 pebbles. " +
                "His board state is: 3,3,3,3,3,0. His store contains 1 pebble.");
        storyboard.markCodeStart();
        game.setCurrentTurn(alex.getPlayerNumber());
        game.getP2Houses().get(5).redistributeCounterClockwise();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,alex);

        storyboard.assertEquals("Alex's House 1:",3,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Alex's House 2:",3,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Alex's House 3:",3,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Alex's House 4:",3,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Alex's House 5:",3,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Alex's House 6:",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Alex's Store:",1,game.getStoreCount(alex.getPlayerNumber()));

        //=======================================================
        storyboard.add("3.It's Jess's turn. Jess redistributes her last 3 pebbles in her row. " +
                "Jess’s board state is 4,4,3,3,3,0. Her store contains 1 pebble.");
        storyboard.markCodeStart();
        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP1Houses().get(5).redistributeCounterClockwise();
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 1:",4,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2:",4,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3:",3,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4:",3,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5:",3,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6:",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store:",1,game.getStoreCount(alex.getPlayerNumber()));
        //========================================================
        storyboard.add("4.It's Alex's turn. Alex redistributes the one pebble from his store to Jess's House 1. " +
                "His board state is 0,3,3,3,4,4. His store contains 0 pebbles.");
        storyboard.markCodeStart();
        game.setCurrentTurn(alex.getPlayerNumber());
        game.getP1Houses().get(0).setPebbles(
                game.getP1Houses().get(0).getPebbles()+game.getP2Houses().get(0).getStore().getPebbles());
        game.getP2Houses().get(0).getStore().setPebbles(0);
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,alex);

        storyboard.assertEquals("Alex's House 1:",4,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Alex's House 2:",4,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Alex's House 3:",3,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Alex's House 4:",3,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Alex's House 5:",3,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Alex's House 6:",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Alex's Store:",0,game.getStoreCount(alex.getPlayerNumber()));

        //========================================================
        storyboard.add("5.It's Jess's turn. Jess redistributes the one pebble from her store to Alex's House 1. " +
                "Her board state is 5,4,3,3,3,0. Her store contains 0 pebbles.");
        storyboard.markCodeStart();
        game.setCurrentTurn(jess.getPlayerNumber());
        game.getP2Houses().get(0).setPebbles(
               game.getP2Houses().get(0).getPebbles()+game.getP1Houses().get(0).getStore().getPebbles());
        game.getP1Houses().get(0).getStore().setPebbles(0);
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess);

        storyboard.assertEquals("Jess's House 1:",5,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2:",4,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3:",3,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4:",3,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5:",3,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6:",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store:",0,game.getStoreCount(alex.getPlayerNumber()));

        //=========================================================
        storyboard.add("6.Jess and Alex leave the game after an unsuccessful attempt.");
        storyboard.markCodeStart();
        game.removePlayer(jess);
        game.removePlayer(alex);
        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game);

        storyboard.assertTrue("Jess and Alex left the game:",game.getPlayers().isEmpty());

        storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../../doc/Scenario6.html'>Scenario6.html</a>
 */
   @Test
    public void testScenario6(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("1.Alex and Jess setup the board to play.");
        storyboard.markCodeStart();

        MancalaGame game = new MancalaGame(6);

        Player jess = new Player();
        jess.setName("Jess");
        jess.setGame(game);

        Player alex =  new Player();
        alex.setName("Alex");
        alex.setGame(game);

        storyboard.addCode();
        storyboard.addObjectDiagram(game,alex,jess);
        storyboard.assertTrue("Jess is a player",game.getPlayers().contains(jess));
        storyboard.assertTrue("Alex is a player",game.getPlayers().contains(alex));

        //=====================================================

        storyboard.add("2. It is Alex's turn. Alex’s board state is: 0,0,0,0,1,0. His store contains 14 pebbles. " +
                "Jess’s board state is: 0,0,0,2,1,4. Her store contains 14 pebbles. ");
        storyboard.markCodeStart();

        game.setCurrentTurn(alex.getPlayerNumber());

        game.getP1Houses().get(0).setPebbles(0);
        game.getP1Houses().get(1).setPebbles(0);
        game.getP1Houses().get(2).setPebbles(0);
        game.getP1Houses().get(3).setPebbles(2);
        game.getP1Houses().get(4).setPebbles(1);
        game.getP1Houses().get(5).setPebbles(4);

        House p1Store = game.getP1Houses().get(0).getStore();
        p1Store.setPebbles(14);

        game.getP2Houses().get(0).setPebbles(0);
        game.getP2Houses().get(1).setPebbles(0);
        game.getP2Houses().get(2).setPebbles(0);
        game.getP2Houses().get(3).setPebbles(0);
        game.getP2Houses().get(4).setPebbles(1);
        game.getP2Houses().get(5).setPebbles(0);

        House p2Store = game.getP2Houses().get(0).getStore();
        p2Store.setPebbles(14);

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,alex);

        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",1,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Alex's Store: ",14,game.getStoreCount(alex.getPlayerNumber()));

        storyboard.assertEquals("Jess's House 1: ",0,game.getP1Houses().get(0).getPebbles());
        storyboard.assertEquals("Jess's House 2: ",0,game.getP1Houses().get(1).getPebbles());
        storyboard.assertEquals("Jess's House 3: ",0,game.getP1Houses().get(2).getPebbles());
        storyboard.assertEquals("Jess's House 4: ",2,game.getP1Houses().get(3).getPebbles());
        storyboard.assertEquals("Jess's House 5: ",1,game.getP1Houses().get(4).getPebbles());
        storyboard.assertEquals("Jess's House 6: ",4,game.getP1Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's Store: ",14,game.getStoreCount(jess.getPlayerNumber()));

        //======================================================

        storyboard.add("3.Alex moves his one pebble to the first empty house. " +
                "Because it landed in an empty house, Alex grabs all 4 of Jess’s " +
                "pebbles from her adjacent house along with his last pebble and places them in his store.");
        storyboard.markCodeStart();

        game.getP2Houses().get(4).redistributeCounterClockwise();

        House p2store = game.getP2Houses().get(0).getStore();

        p2store.setPebbles(p2store.getPebbles()+game.getP1Houses().get(5).getPebbles());
        game.getP1Houses().get(5).setPebbles(0);

        game.setCurrentTurn(alex.getPlayerNumber());
        game.getP2Houses().get(5).redistributeCounterClockwise();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess,alex);

        storyboard.assertEquals("Alex's House 6:",0, game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Jess's House 6:",0, game.getP1Houses().get(5).getPebbles());
        storyboard.assertEquals("Alex's Store:",19,game.getStoreCount(alex.getPlayerNumber()));

        //=====================================================
        storyboard.add("Alex wins the game with a cleared board and 19 stones in his store.");
        storyboard.markCodeStart();

        storyboard.addCode();
        storyboard.addObjectDiagramOnlyWith(game,jess,alex);

        storyboard.assertEquals("Alex's House 1: ",0,game.getP2Houses().get(0).getPebbles());
        storyboard.assertEquals("Alex's House 2: ",0,game.getP2Houses().get(1).getPebbles());
        storyboard.assertEquals("Alex's House 3: ",0,game.getP2Houses().get(2).getPebbles());
        storyboard.assertEquals("Alex's House 4: ",0,game.getP2Houses().get(3).getPebbles());
        storyboard.assertEquals("Alex's House 5: ",0,game.getP2Houses().get(4).getPebbles());
        storyboard.assertEquals("Alex's House 6: ",0,game.getP2Houses().get(5).getPebbles());
        storyboard.assertEquals("Alex's Store:",19,game.getStoreCount(alex.getPlayerNumber()));
        storyboard.assertTrue("Alex wins the game.",game.getStoreCount(
                alex.getPlayerNumber())>game.getStoreCount(jess.getPlayerNumber()));
        storyboard.dumpHTML();
    }
}
