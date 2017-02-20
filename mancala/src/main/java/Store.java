/**
 * Created by bbui1 on 2/16/2017.
 */
public class Store {
    private int pebbles;
    private Board board;

    public int getPebbles() {
        return pebbles;
    }

    public void setPebbles(int num) {
        if(num < 0) throw new IllegalArgumentException("setting invalid amount pebbles");
        this.pebbles = num;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
