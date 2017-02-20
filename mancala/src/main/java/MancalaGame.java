import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class MancalaGame {
    private Set<Player> players= new LinkedHashSet<Player>();
    private LinkedList<House> leftHouses = new LinkedList<House>();
    private LinkedList<House> rightHouses = new LinkedList<House>();
    private House leftStore;
    private House rightStore;

    public MancalaGame(int numHouses){
        makeBoard(numHouses);

    }
    public void makeBoard(int numHouses){
        leftStore = new House();
        leftStore.setPebbles(0);
        leftStore.setStore(true);
        rightStore = new House();
        rightStore.setPebbles(0);
        rightStore.setStore(true);

        for(int i = 0; i < numHouses; i++){
            House lefthouse = new House();
            lefthouse.setPebbles(3);
            lefthouse.setStore(leftStore);
            House righthouse = new House();
            righthouse.setPebbles(3);
            righthouse.setStore(rightStore);
            leftHouses.add(lefthouse);
            rightHouses.add(righthouse);
        }
        for(int i =0; i < numHouses-2; i++) {
            leftHouses.get(i).setNext(leftHouses.get(i + 1));
            rightHouses.get(i).setNext(rightHouses.get(i + 1));
        }

        //make the circle
        leftHouses.get(numHouses-1).setNext(leftStore);
        rightHouses.get(numHouses-1).setNext(rightStore);
        leftStore.setNext(rightHouses.get(0));
        rightStore.setNext(leftHouses.get(0));
        //link across (preserves the opposite relationship)
        for(int i =0; i < numHouses; i++){
            leftHouses.get(i).setAcross(rightHouses.get((numHouses-i)-1));
            rightHouses.get(i).setAcross(leftHouses.get((numHouses-i)-1));
        }


    }
    public LinkedList<House> getLeftHouses(){
        return leftHouses;
    }
    public LinkedList<House> getRightHouses(){
        return rightHouses;
    }


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


}
