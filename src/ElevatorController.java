public class ElevatorController {

    /**
     * Handles the request to move the elevator to a specific floor.
     *
     * @param elevator The elevator instance.
     * @param floor    The floor to which the elevator is requested to move.
     */
    public static void goToFloor(Elevator elevator, int floor){

        // Print a message indicating a call to a specific floor
        System.err.println("Call for "+floor+" floor");

        // Check if the elevator is already at the requested floor
        if(elevator.currentfloor == floor){
            System.out.println("Lift is already at floor...Door Opening");
        }

        // Handle the direction of the elevator and add the floor to the appropriate queue
        if(elevator.direction == Direction.UP){
            handleUpDirection(elevator, floor);
        } else if(elevator.direction == Direction.DOWN){
            handleDownDirection(elevator, floor);
        } else { // Lift is stationary
            handleStationaryLift(elevator, floor);
        }
    }

    /**
     * Handles the case when the elevator is moving in the UP direction.
     *
     * @param elevator The elevator instance.
     * @param floor    The floor to which the elevator is requested to move.
     */
    private static void handleUpDirection(Elevator elevator, int floor) {
        int timeToReachOriginalDirection = calculateTimeToReach(elevator, elevator.direction);
        int timeToReachNewDirection = calculateTimeToReach(elevator, Direction.UP);

        if (timeToReachNewDirection < 3 && timeToReachNewDirection < timeToReachOriginalDirection) {
            // Add the floor to the UP queue
            elevator.queueUp.add(floor);
        } else {
            // Add the floor to the original direction queue
            if (elevator.currentfloor < floor) {
                elevator.queueUp.add(floor);
            } else {
                elevator.queueDown.add(floor);
            }
        }
    }

    private static void handleDownDirection(Elevator elevator, int floor) {
        int timeToReachOriginalDirection = calculateTimeToReach(elevator, elevator.direction);
        int timeToReachNewDirection = calculateTimeToReach(elevator, Direction.DOWN);

        if (timeToReachNewDirection < 3 && timeToReachNewDirection < timeToReachOriginalDirection) {
            // Add the floor to the DOWN queue
            elevator.queueDown.add(floor);
        } else {
            // Add the floor to the original direction queue
            if (elevator.currentfloor > floor) {
                elevator.queueDown.add(floor);
            } else {
                elevator.queueUp.add(floor);
            }
        }
    }

    private static int calculateTimeToReach(Elevator elevator, Direction direction) {
        // Simple calculation for demonstration purposes
        // You may need a more sophisticated approach in a real-world scenario
        int distance = Math.abs(elevator.currentfloor - elevator.targetFloor);
        int speed = 1; // Assume a constant speed for simplicity
        return distance / speed;
    }


    /**
     * Handles the case when the elevator is stationary.
     *
     * @param elevator The elevator instance.
     * @param floor    The floor to which the elevator is requested to move.
     */
    private static void handleStationaryLift(Elevator elevator, int floor) {
        if(elevator.currentfloor > floor){
            // Add the floor to the DOWN queue
            elevator.queueDown.add(floor);
        } else if(elevator.currentfloor < floor){
            // Add the floor to the UP queue
            elevator.queueUp.add(floor);
        }
    }



}
