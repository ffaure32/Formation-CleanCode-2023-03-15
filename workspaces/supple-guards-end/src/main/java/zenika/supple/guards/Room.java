package zenika.supple.guards;

public class Room {

    private final int capacity;
 
    public Room(int capacity) {
        if(capacity<=0) {
            throw new IllegalArgumentException("Room must have at least a capacity of 1");
        }
        this.capacity = capacity;
    }
    
    public int capacity() {
        return this.capacity;
    }

}
