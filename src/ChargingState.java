public class ChargingState implements IRobotState {
    private final Robot robot;

    public ChargingState(Robot robot) { this.robot = robot; }

    @Override
    public void enter() {
        System.out.println(robot.getRobotId() + ": вошёл в состояния зарядки");
    }

    @Override
    public void execute() {
            System.out.println(robot.getRobotId() + ": заряжаюсь...");
            robot.getPowerManager().charge();
            robot.startIdle();
    }

    @Override
    public void exit() { System.out.println(robot.getRobotId() + ": вышел из состояния зарядки"); }

    @Override
    public RobotStatus getStatus() { return RobotStatus.CHARGING; }

    @Override
    public IRobotState clone(Robot newRobot) {
        return new ChargingState(newRobot);
    }
}
