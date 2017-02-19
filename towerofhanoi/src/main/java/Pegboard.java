/**
 * Created by delta on 2/18/2017.
 */
import java.util.ArrayList;

public class PegBoard {
    private int numberOfDisks;
    private int numberOfPegs;
    private ArrayList<Disk> disks;
    private ArrayList<Peg> pegs;

    //PegBoard Constructor
    public PegBoard(){
        this.numberOfDisks = 0; //Initialize disk counter
        this.numberOfPegs = 0;  //Initialize peg counter
        this.disks = new ArrayList<>(); //Initialize disk array
        this.pegs = new ArrayList<>(); //Initialize peg array
    }

    //Add a disk to the board
    public boolean addDisk(Disk disk){
        if(disk.equals(null)){
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
    public boolean addPeg(Peg peg){
        if(peg.equals(null)){
            throw new IllegalArgumentException("NULL peg object");
        }

        //Add peg to list
        if(this.pegs.add(peg)){
            this.numberOfPegs++; //increase number of pegs
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
    public ArrayList<Disk> getDisks(){
        return this.disks;
    }

    //Get pegs on board
    public ArrayList<Peg> getPegs(){
        return this.pegs;
    }

}
