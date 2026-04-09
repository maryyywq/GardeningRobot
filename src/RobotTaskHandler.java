public class RobotTaskHandler extends TaskHandler {
    private final Robot robot;

    public RobotTaskHandler(Robot robot) {
        this.robot = robot;
    }

    @Override
    protected boolean canHandle(ICommand command) {
        return command.canBeHandledBy(robot);
    }

    @Override
    protected boolean doHandle(ICommand command) {
        System.out.println("Робот " + robot.getRobotId() + " пытается выполнить команду");
        command.execute(robot);   // передаём робота в команду
        return robot.getStatus() != RobotStatus.ERROR;
    }

    @Override
    public boolean handle(ICommand command) {
        if (command.canBeHandledBy(robot)) {
            System.out.println("Робот " + robot.getRobotId() + " выполняет команду");
            command.execute(robot);
            return robot.getStatus() != RobotStatus.ERROR;
        } else {
            System.out.println("Робот " + robot.getRobotId() + " не может выполнить команду");
            if (next != null) {
                return next.handle(command);
            } else {
                System.out.println("Ни один робот не смог выполнить команду");
                return false;
            }
        }
    }
}