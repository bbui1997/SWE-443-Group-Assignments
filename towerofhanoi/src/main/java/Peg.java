/*
 * Peg Class
 **/

//import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * author: Marcus Yarbrough
 * author: Victor Nguyen
 * author: Sam Luu
 */
public class Peg {

    private String pegPosition;
    private Stack<Disk> disksOnPeg; //used a stack instead of an Arraylist to make things more understandable

    public Peg(){
        this.pegPosition = null;
        this.disksOnPeg = new Stack<>();
    }

    public Stack<Disk> getDisksOnPeg() {
        final Stack<Disk> retVal;
        return retVal = this.disksOnPeg;
    }

    //GETTERS and SETTERS to get the pegPosition
    public String getPegPosition(){
        return this.pegPosition;
    }

    public void setPegPosition(String newPeg) throws NullPointerException{
        if (newPeg == null) {
            throw new NullPointerException("newPeg is null");
        }
        this.pegPosition = newPeg;
    }

    public boolean putDiskOntoPeg(Disk newDisk){

        if(disksOnPeg.push(newDisk) == null){
            return false;
        }
        return true;
    }

    public void removeTopDisk(){
        this.disksOnPeg.pop();
    }

}