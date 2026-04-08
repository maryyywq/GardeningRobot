public class FertilizeCommand extends TaskCommand {
    private final String fertilizerType;

    public FertilizeCommand(Robot robot, String fertilizerType) {
        super(robot, ToolType.FERTILIZING, "Удобрение");
        this.fertilizerType = fertilizerType;
    }

    @Override
    protected void executeTask(ITool tool) {
        if (fertilizerType == null || fertilizerType.isEmpty()) {
            System.out.println(robot.getRobotId() + ": ошибка - не указан тип удобрения");
            return;
        }
        System.out.println(robot.getRobotId() + ": внесение удобрения " + fertilizerType);
        tool.execute();
    }
}