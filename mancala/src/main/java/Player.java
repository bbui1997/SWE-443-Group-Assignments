import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


public class Player {
    private String name;
    private MancalaGame game;
   // private House store;          To be implemented later
    //private LinkedList<House> myhouses;  To be implemented later

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) throw new NullPointerException("player name cannot be null");
        this.name = name;
    }

    public void setGame(MancalaGame newGame){
        if(this.game != newGame){
            MancalaGame oldVal = this.game;
            this.game = newGame;
            if(oldVal != null){
                oldVal.removePlayer(this);
            }
            if(this.getGame() != null){
                this.getGame().addPlayer(this);
            }
        }
    }

    public MancalaGame getGame(){ return this.game; }

}
