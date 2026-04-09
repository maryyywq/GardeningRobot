public class MoveCommand implements ICommand {
    private final Location destination;

    public MoveCommand(Location destination) {
        this.destination = destination;
    }

    @Override
    public void execute(Robot robot) {
        robot.startMoving(destination);
        robot.act();
        robot.setCurrentSegment(robot.getSegmentFactory().getMapSegment(destination));
        robot.notifyRobotObservers(new RobotEvent(robot.getRobotId(), EventType.ARRIVED, destination));
    }

    @Override
    public boolean canBeHandledBy(Robot robot) {
        return true;
    }

    @Override
    public String toString() {
        return "Перемещение в " + destination;
    }

    @Override
    public boolean isMadeOnThisSegment() { return false; }
}