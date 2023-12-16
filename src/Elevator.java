import java.util.PriorityQueue;

public class Elevator{

    private static Elevator elevator1;

    int currentfloor;
    int startFloor;
    int endFloor;
    int targetFloor;
    Direction direction;
    PriorityQueue<Integer> queueUp;
    PriorityQueue<Integer> queueDown;

    private Elevator(){
        this.currentfloor = 0;
        this.startFloor = 0;
        this.endFloor = 50;
        this.targetFloor = 0;
        this.direction = Direction.STOP;
        this.queueDown = new PriorityQueue<>((a,b)->(b-a)); //The queueDown is initialized with a comparator that orders elements in descending order.
        this.queueUp = new PriorityQueue<>();               // queueUp is default

    }
    /**
     * Returns the singleton instance of the Elevator class.
     * If the instance does not exist, a new instance is created.
     * This method ensures that only one instance of Elevator exists throughout the application.
     *
     * @return The singleton instance of the Elevator class.
     */
    public synchronized static Elevator getElevator() {
        // Check if the elevator instance is null (not created yet)
        if (elevator1 == null) {
            // Create a new instance of Elevator
            elevator1 = new Elevator();
        }
        // Return the existing or newly created instance the next time elevator is called
        return elevator1;
    }


}

