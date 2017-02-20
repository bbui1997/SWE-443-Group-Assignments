
public class House {
    private int pebbles;
    private boolean isStore = false;
    private House next;
    private House across;
    private House store;

    public void setStore(House house){
        this.store = house;
    }
    public House getStore(House house){
        return this.store;
    }
    public void setNext(House house){
        this.next = house;
    }
    public void setAcross(House house){
        this.across = house;

    }
    public House getNext(){
        return this.next;
    }
    public House getAcross(){
        return this.across;
    }

    public boolean getIsStore(){
        return this.isStore;
    }

    public House getStore(){
        return this.store;
    }

    public void setStore(boolean val){
        this.isStore = true;
    }

    public int getPebbles() {
        return pebbles;
    }

    public void setPebbles(int num) {
        if(num < 0) throw new IllegalArgumentException("setting invalid amount pebbles");
        this.pebbles = num;
    }



    public void takeOppositePebbles(){
        if (this.isStore) {
            throw new IllegalStateException("Cannot take opposite pebbles from a store");
        }
        else {
            this.store.setPebbles(this.across.getPebbles()+this.store.getPebbles());
            this.across.setPebbles(0);
        }

    }
    //Recursive
    public void redistributeCounterClockwise() {
        this.next.redistributeCounterClockwiseRecurse(this.pebbles, this.store);
        this.pebbles = 0;

    }
    public void redistributeCounterClockwiseRecurse(int pebbles_in_hand, House store) {
        if (this.getIsStore() && this != store) {
            this.next.redistributeCounterClockwiseRecurse(pebbles_in_hand, store);

        }
            if (pebbles_in_hand != 0) {
                this.pebbles += 1;
                this.next.redistributeCounterClockwiseRecurse(pebbles_in_hand - 1, store);
            }
    }
}

