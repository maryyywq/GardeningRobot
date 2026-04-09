public abstract class TaskCommand implements ICommand {
    protected final ToolType toolType;
    protected final String taskName;

    public TaskCommand(ToolType toolType, String taskName) {
        this.toolType = toolType;
        this.taskName = taskName;
    }

    public ToolType getToolType() {
        return toolType;
    }

    @Override
    public boolean canBeHandledBy(Robot robot) {
        ToolPool pool = robot.getToolPool();
        if (pool == null) return false;

        ITool tool = pool.acquireTool(toolType);
        if (tool == null) return false;
        pool.releaseTool(tool);

        if (!robot.canUseTool(tool)) return false;

        double requiredEnergy = tool.getPowerConsumption();
        PowerAction action = robot.getPowerManager().checkPower(requiredEnergy);
        return action == PowerAction.CONTINUE;
    }

    @Override
    public void execute(Robot robot) {
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
                executeTask(robot, tool);
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
                executeTask(robot, tool);
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

    protected abstract void executeTask(Robot robot, ITool tool);
}