public class MowCommand extends TaskCommand {
    private final double height;

    public MowCommand(double height) {
        super(ToolType.MOWING, "Стрижка");
        this.height = height;
    }

    @Override
    protected void executeTask(Robot robot, ITool tool) {
        if (height <= 0) {
            System.out.println(robot.getRobotId() + ": ошибка - высота стрижки должна быть положительной");
            return;
        }
        System.out.println(robot.getRobotId() + ": стрижка на высоту " + height + " см");
        tool.execute();
    }

    @Override
    public String toString() {
        return "Стрижка на высоту " + height + " см";
    }
}
