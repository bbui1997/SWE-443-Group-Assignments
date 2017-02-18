/**
 * Created by delta on 2/18/2017.
 */
public class Disk {
    private int diskSize;
    private int diskNumber;
    private Peg currentPeg;

    /* PUBLIC DEFAULT CONSTRUCTOR */
    public Disk() {
        this.diskSize = 0;
        this.diskNumber = 0;
        this.currentPeg = null;
    }


    /* GETTER AND SETTER METHODS FOR VARIABLE diskSize */
    public int getDiskSize() {
        return this.diskSize;
    }
    public void setDiskSize(int diskSize) {
        if (diskSize < 1)
            throw new IllegalArgumentException("diskSize cannot be less than 1");
        this.diskSize = diskSize;
    }


    /* GETTER AND SETTER METHODS FOR VARIABLE diskNumber */
    public int getDiskNumber() {
        return this.diskNumber;
    }
    public void setDiskNumber(int diskNumber) {
        if (diskNumber < 1)
            throw new IllegalArgumentException("diskNumber cannot be less than 1");
        this.diskNumber = diskNumber;
    }


    /* GETTER AND SETTER METHODS FOR VARIABLE currentPeg*/
    public Peg getCurrentPeg() {
        return this.currentPeg;
    }
    public void setCurrentPeg(Peg newPeg) {
        if (this.currentPeg != newPeg) {
            Peg prevPeg = this.currentPeg;
            this.currentPeg = newPeg;
            if (prevPeg != null);
                //prevPeg.pop(this);
            if (this.currentPeg != null);
                //prevPeg.push(this);        // I was thinking about have each peg object contain a stack (data structure) of disks
        }
    }
}
