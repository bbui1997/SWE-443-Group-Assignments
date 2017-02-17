import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Created by bbui1 on 2/15/2017.
 */
public class MancalaGame {
    private Set<Player> players= new LinkedHashSet<Player>();
    private Board board;


    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public boolean addPlayer(Player p) {
        boolean addSuccess = players.add(p);
        if(addSuccess && p != null){
            p.setGame(this);
        }

        return addSuccess;
    }

    public boolean removePlayer(Player p){
        boolean changed = players.remove(p);
        if(changed && p != null){
            p.setGame(null);
        }
        return changed;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
