import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * author: Marcus Yarbrough
 * author: Victor Nguyen
 * author: Sam Luu
 */
public class TestDiskMove{

    PegBoard game;
    Disk one;
    Disk two;
    Peg a;
    Peg b;

    /*
     * Setup the board
     */
    @Before
    public void initializeTower(){
        game = new PegBoard();
        one = new Disk();
        two = new Disk();
        a = new Peg();
        b = new Peg();

        one.setDiskSize(1);
        two.setDiskSize(2);

        a.setPegPosition("a");
        b.setPegPosition("b");

        two.setCurrentPeg(a);
        one.setCurrentPeg(a);

        game.addPeg(a);
        game.addPeg(b);
        game.addDisk(one);
        game.addDisk(two);
    }

    /*
     * Test moving one disk to a peg.
     */
    @Test
    public void testMove() throws Exception {
        assertTrue(one.move(b));
    }


    /*
     * Test moving a large object on top of a smaller object.
     * Throws IllegalStateException.
     */
    @Test (expected = IllegalStateException.class)
    public void testMoveException(){
        one.move(b);
        two.move(b);
    }
}