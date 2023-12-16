public class Main {


    public static void main(String[] args) throws Exception {

        // Start the elevator server
        ElevatorConsole server = new ElevatorConsole() ;
        new Thread(server).start() ;

        // Get the instance of Elevator
        Elevator elevator = Elevator.getElevator();

        // Move Elevator
        ElevatorController.goToFloor(elevator, 2);
        ElevatorController.goToFloor(elevator, 5);
        ElevatorController.goToFloor(elevator, 9);
        ElevatorController.goToFloor(elevator, 6);
        ElevatorController.goToFloor(elevator, 1);
        ElevatorController.goToFloor(elevator, 8);

        Thread.sleep(10000);
        ElevatorController.goToFloor(elevator, 1);

        Thread.sleep(2000);
        ElevatorController.goToFloor(elevator, 2);
        ElevatorController.goToFloor(elevator, 3);
        ElevatorController.goToFloor(elevator, 7);
    }

}



