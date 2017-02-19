/*
 * Created by delta on 2/18/2017.
 **/

//import java.util.ArrayList;
import java.util.Stack;

public class Peg {

    private String pegPosition;
    private Stack<Disk> disksOnPeg; //used a stack instead of an Arraylist to make things more understandable
    //public ArrayList<Disk> disksOnPeg;

    public Peg(String peg){
        this.pegPosition = peg;
        this.disksOnPeg = new Stack<>();
    }

    //GETTERS and SETTERS to get the pegPosition
    public String getPegPostion(){
        return this.pegPosition;
    }

    public void setPegPostion(String newPeg){
        this.pegPosition = newPeg;
    }

    public void putDiskOntoPeg(Disk newDisk){
        if(disksOnPeg.isEmpty()){
            disksOnPeg.push((newDisk));
        }
        else{
            //this checks the top of the stack to see if the disk is the correct size in in pertaining to the rules of the Tower of Hanoi game
            if((disksOnPeg.peek().getDiskSize()) < (newDisk.getDiskSize())){
                throw new IllegalStateException("Cannot place onto Peg while current disk is smaller than new Disk");
            }
            else{
                disksOnPeg.push(newDisk);
            }
        }
    }
    public void removeTopDisk(){
        this.disksOnPeg.pop();
    }

}