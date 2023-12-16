public interface Physical {

    public void startMovingUp(Elevator e);

    public void startMovingDown(Elevator e);

    public boolean isApproachingFloor(Elevator e, int floor);
}
