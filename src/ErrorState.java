public class ErrorState implements IRobotState {
    private final Robot robot;

    public ErrorState(Robot robot) { this.robot = robot; }

    @Override
    public void enter() { System.out.println(robot.getRobotId() + ": вошёл в состояние ошибки"); }

    @Override
    public void execute() {
        System.out.println(robot.getRobotId() + ": не могу выполнить задачу, нахожусь в состоянии ошибки");
        System.out.println(robot.getRobotId() + ": попытка выхода из состояния ошибки");
        robot.resetError();
    }

    @Override
    public void exit() { System.out.println(robot.getRobotId() + ": вышел из состояния ошибки"); }

    @Override
    public RobotStatus getStatus() { return RobotStatus.ERROR; }

    @Override
    public IRobotState clone(Robot newRobot) {
        return new ErrorState(newRobot);
    }
}
