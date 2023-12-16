public class ElevatorConsole implements Runnable, Physical {

    // Shared instance of the Elevator class
    final static Elevator elevator = Elevator.getElevator();

    /**
     * Continuously serves lift requests.
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Sleep for 2 seconds (simulating periodic check for requests)
                try { Thread.sleep(2000); } catch (Exception e) { }

                // Check for lift requests and update target location based on direction
                if (elevator.direction == Direction.UP) {
                    if (!elevator.queueUp.isEmpty()) {
                        elevator.targetFloor = elevator.queueUp.peek();
                    }
                } else if (elevator.direction == Direction.DOWN) {
                    if (!elevator.queueDown.isEmpty()) {
                        elevator.targetFloor = elevator.queueDown.peek();
                    }
                } else {
                    if (!elevator.queueUp.isEmpty()) {
                        elevator.targetFloor = elevator.queueUp.peek();
                    } else if (!elevator.queueDown.isEmpty()) {
                        elevator.targetFloor = elevator.queueDown.peek();
                    }
                }

                // Update the direction based on the target floor
                if (elevator.currentfloor < elevator.targetFloor) {
                    elevator.direction = Direction.UP;
                } else if (elevator.currentfloor > elevator.targetFloor) {
                    elevator.direction = Direction.DOWN;
                } else {
                    elevator.direction = Direction.STOP;
                }

                // Check if the elevator is stationary or moving to a target floor
                if (elevator.queueDown.isEmpty() && elevator.queueUp.isEmpty()) {
                    System.out.println("Lift is Stationary at floor " + elevator.currentfloor);
                }
                else {
                    // Handle elevator movement
                    if (elevator.currentfloor < elevator.targetFloor) {
                        startMovingUp(elevator);
                        elevator.currentfloor++;
                    }
                    else if (elevator.currentfloor > elevator.targetFloor) {
                        startMovingDown(elevator);
                        elevator.currentfloor--;
                    }

                    // Check if the elevator has reached a floor
                    if (elevator.currentfloor == elevator.targetFloor) {
                        // Serve the request if the target floor matches the current direction
                        if (!elevator.queueDown.isEmpty() && elevator.queueDown.peek() == elevator.targetFloor) {
                            elevator.queueDown.poll();  // Request served
                        }
                        else if (!elevator.queueUp.isEmpty() && elevator.queueUp.peek() == elevator.targetFloor) {
                            elevator.queueUp.poll();    // Request served
                        }
                        System.out.println("*** Elevator Reached at " + elevator.targetFloor + " ***\nDoor Opening...\n");
                    }

                    // Print elevator details and current status
                    System.out.println("Elevator{" +
                            "\n currentfloor=	" + elevator.currentfloor +
                            ",\n targetFloor=	" + elevator.targetFloor +
                            ",\n direction=		" + elevator.direction +
                            ",\n queueUp=		" + elevator.queueUp +
                            ",\n queueDown=		" + elevator.queueDown +
                            "\n");

                    System.out.println("Elevator at " + elevator.currentfloor + " and going " + elevator.direction);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Method to be implemented (not specified in the provided code)
    @Override
    public void startMovingUp(Elevator e) {

    }

    // Method to be implemented (not specified in the provided code)
    @Override
    public void startMovingDown(Elevator e) {

    }

    /**
     * Checks if the elevator is approaching a specific floor.
     *
     * @param e     The elevator instance.
     * @param floor The target floor to check.
     * @return True if the elevator is approaching the specified floor, false otherwise.
     */
    @Override
    public boolean isApproachingFloor(Elevator e, int floor) {
        if ((e.currentfloor < floor && e.direction == Direction.UP) ||
                (e.currentfloor > floor && e.direction == Direction.DOWN)) {
            return true;
        }
        return false;
    }
}
