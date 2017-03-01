/* Disk Class
 * author: Marcus Yarbrough
 * author: Victor Nguyen
 * author: Sam Luu
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
    public int getDiskSize() throws IllegalArgumentException {
        return this.diskSize;
    }

    public void setDiskSize(int diskSize) throws IllegalArgumentException {
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
        this.currentPeg = newPeg;
        newPeg.putDiskOntoPeg(this);
    }

    /* Move disk onto a peg */
    public boolean move(Peg peg) throws IllegalStateException{
        if(peg == null){ //check if peg object null
            throw new IllegalArgumentException("NULL peg object");
        }

        //check disk size for proper placement
        if(!(peg.getDisksOnPeg().isEmpty())){
            if(this.getDiskSize() > peg.getDisksOnPeg().peek().getDiskSize()) {
                throw new IllegalStateException("Cannot place onto Peg while current disk is smaller than new Disk");
            }
        }

        if (this.currentPeg.getDisksOnPeg().peek() == this) {
            this.currentPeg.removeTopDisk();	//

            if(peg.putDiskOntoPeg(this)){ //put disk on peg
                this.setCurrentPeg(peg); //update current peg
                return true;
            }
        }

        return false;
    }
}