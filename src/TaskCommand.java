public abstract class TaskCommand implements ICommand {
    protected final Robot robot;
    protected final ToolType toolType;
    protected final String taskName;

    public TaskCommand(Robot robot, ToolType toolType, String taskName) {
        this.robot = robot;
        this.toolType = toolType;
        this.taskName = taskName;
    }

    @Override
    public void execute() {
        ToolPool pool = robot.getToolPool();
        if (pool == null) {
            System.out.println(robot.getRobotId() + ": нет доступа к пулу инструментов");
            robot.handleError();
            return;
        }

        ITool tool = pool.acquireTool(toolType);
        if (tool == null) {
            System.out.println(robot.getRobotId() + ": нет свободного инструмента " + toolType);
            robot.handleError();
            return;
        }

        if (!robot.canUseTool(tool)) {
            System.out.println(robot.getRobotId() + ": инструмент несовместим с базой знаний");
            pool.releaseTool(tool);
            robot.handleError();
            return;
        }

        robot.setCurrentTool(tool);

        double requiredEnergy = tool.getPowerConsumption();
        PowerAction action = robot.getPowerManager().checkPower(requiredEnergy);

        switch (action) {
            case CONTINUE:
                robot.notifyRobotObservers(new RobotEvent(robot.getRobotId(), EventType.TASK_STARTED, taskName));
                System.out.println(robot.getRobotId() + ": выполняю " + taskName);
                executeTask(tool);
                robot.getPowerManager().consumeEnergy(requiredEnergy);
                robot.notifyRobotObservers(new RobotEvent(robot.getRobotId(), EventType.TASK_COMPLETED, taskName));
                break;

            case CHARGE:
                System.out.println(robot.getRobotId() + ": недостаточно энергии, иду на зарядку");
                robot.startCharging();
                robot.act();
                break;

            case USE_BACKUP:
                robot.getPowerManager().switchToBackup();
                executeTask(tool);
                robot.getPowerManager().consumeEnergy(requiredEnergy);
                break;

            case STOP:
                robot.notifyRobotObservers(new RobotEvent(robot.getRobotId(), EventType.TASK_FAILED, "insufficient power"));
                System.out.println(robot.getRobotId() + ": критически мало энергии, задача отменена");
                robot.handleError();
                break;
        }

        pool.releaseTool(tool);
        robot.setCurrentTool(null);
    }

    protected abstract void executeTask(ITool tool);
}