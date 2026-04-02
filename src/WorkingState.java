public class WorkingState implements IRobotState {
    private final Robot robot;

    public WorkingState(Robot robot) { this.robot = robot; }

    @Override
    public void enter() { System.out.println(robot.getRobotId() + ": вошёл в состояние работы"); }

    @Override
    public void execute() {
        // Проверяем наличие инструмента
        if (robot.getCurrentTool() == null) {
            System.err.println(robot.getRobotId() + ": нет инструмента, ошибка");
            robot.handleError();
            return;
        }

        double requiredEnergy = robot.getCurrentTool().getPowerConsumption();
        PowerAction action = robot.getPowerManager().checkPower(requiredEnergy);

        switch (action) {
            case CONTINUE:
                System.out.println(robot.getRobotId() + ": выполняю задачу инструментом " +
                        robot.getCurrentTool().getName() + " (потребление " + requiredEnergy + ")");
                robot.getCurrentTool().execute();
                robot.getPowerManager().consumeEnergy(requiredEnergy);
                robot.startIdle();
                break;

            case CHARGE:
                System.out.println(robot.getRobotId() + ": недостаточно энергии (нужно " +
                        requiredEnergy + "), иду заряжаться");
                robot.startCharging();
                break;

            case USE_BACKUP:
                System.out.println(robot.getRobotId() + ": переключаюсь на резервный источник");
                robot.getPowerManager().switchToBackup();
                break;

            case STOP:
                System.err.println(robot.getRobotId() + ": недостаточно энергии, задача отменена");
                robot.handleError();
                break;

            default:
                robot.handleError();
        }
    }

    @Override
    public void exit() { System.out.println(robot.getRobotId() + ": вышел из состояния работы"); }

    @Override
    public RobotStatus getStatus() { return RobotStatus.WORKING; }

    @Override
    public IRobotState clone(Robot newRobot) {
        return new WorkingState(newRobot);
    }

}
