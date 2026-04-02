public class MovingState implements IRobotState {
    private final Robot robot;

    public MovingState(Robot robot) { this.robot = robot; }

    @Override
    public void enter() {
        System.out.println(robot.getRobotId() + ": вошёл в состояние движения");
    }

    @Override
    public void execute() {
        robot.getMovementSystem().moveTo(robot.getDestination());
        robot.startIdle();
    }

    @Override
    public void exit() { System.out.println(robot.getRobotId() + ": вышел из состояния движения"); }

    @Override
    public RobotStatus getStatus() { return RobotStatus.MOVING; }

    @Override
    public IRobotState clone(Robot newRobot) {
        return new MovingState(newRobot);
    }
}
