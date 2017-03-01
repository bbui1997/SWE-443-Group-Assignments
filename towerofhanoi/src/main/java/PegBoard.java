import java.util.*;

/* PegBoard Class
 * author: Marcus Yarbrough
 * author: Victor Nguyen
 * author: Sam Luu
 */
public class PegBoard {
    private int numberOfDisks;
    private int numberOfPegs;
    private List<Disk> disks;
    private List<Peg> pegs;

    //PegBoard Constructor
    public PegBoard(){
        this.numberOfDisks = 0; //Initialize disk counter
        this.numberOfPegs = 0;  //Initialize peg counter
        this.disks = new ArrayList<>(); //Initialize disk array
        this.pegs = new ArrayList<>(); //Initialize peg array
    }

    //Add a disk to the board
    public boolean addDisk(Disk disk){
        if(disk==null){
            throw new IllegalArgumentException("NULL disk object");
        }

        //Add disk to list
        if(this.disks.add(disk)){
            this.numberOfDisks++; //increase number of disks
            return true;
        }
        return false;
    }

    //Add a peg to the board
    public boolean addPeg(Peg peg) throws IllegalArgumentException{
        if(peg==null){
            throw new IllegalArgumentException("NULL peg object");
        }

        //Add peg to list
        if(this.pegs.add(peg)){
            this.numberOfPegs++; //increase number of pegs
            return true;
        }

        return false;
    }

    //Remove a disk from the board
    public boolean removeDisk(Disk disk) throws IllegalArgumentException{
        if(disk==null){ //check for null param
            throw new IllegalArgumentException("NULL disk object");
        }

        if(this.disks.isEmpty()){ //check for empty disk array
            throw new IllegalStateException("No disks exists");
        }

        if(this.disks.remove(disk)){ //remove disk from arraylist
            this.numberOfDisks--;
            return true;
        }
        return false;
    }

    //Remove a peg from the board
    public boolean removePeg(Peg peg) throws IllegalArgumentException,IllegalStateException{
        if(peg==null){ //check for null param
            throw new IllegalArgumentException("NULL peg object");
        }

        if(this.pegs.isEmpty()){
            throw new IllegalStateException("No pegs exists");
        }

        if(this.pegs.remove(peg)){
            this.numberOfPegs--;
            return true;
        }
        return false;
    }

    //Get number of Disks on board
    public int getNumberOfDisks(){
        return this.numberOfDisks;
    }

    //Get number of Pegs on board
    public int getNumberOfPegs(){
        return this.numberOfPegs;
    }

    //Get disks on board
    public List<Disk> getDisks(){
        return Collections.unmodifiableList(this.disks);
    }

    //Get pegs on board
    public List<Peg> getPegs(){
        return Collections.unmodifiableList(this.pegs);
    }

}