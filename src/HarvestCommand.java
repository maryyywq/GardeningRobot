public class HarvestCommand extends TaskCommand {
    public HarvestCommand(Robot robot) {
        super(robot, ToolType.HARVESTING, "Сбор урожая");
    }

    @Override
    protected void executeTask(ITool tool) {
        System.out.println(robot.getRobotId() + ": сбор урожая");
        tool.execute();
    }
}