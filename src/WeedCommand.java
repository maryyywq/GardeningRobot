public class WeedCommand extends TaskCommand {
    public WeedCommand(Robot robot) {
        super(robot, ToolType.WEEDING, "Прополка");
    }

    @Override
    protected void executeTask(ITool tool) {
        System.out.println(robot.getRobotId() + ": удаление сорняков");
        tool.execute();
    }
}