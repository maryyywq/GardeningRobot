public class MowCommand extends TaskCommand {
    private final double height;

    public MowCommand(Robot robot, double height) {
        super(robot, ToolType.MOWING, "Стрижка");
        this.height = height;
    }

    @Override
    protected void executeTask(ITool tool) {
        if (height <= 0) {
            System.out.println(robot.getRobotId() + ": ошибка - высота стрижки должна быть положительной");
            return;
        }
        System.out.println(robot.getRobotId() + ": стрижка на высоту " + height + " см");
        tool.execute();
    }
}