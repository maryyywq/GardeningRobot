public class FertilizeCommand extends TaskCommand {
    private final String fertilizerType;

    public FertilizeCommand(String fertilizerType) {
        super(ToolType.FERTILIZING, "Удобрение");
        this.fertilizerType = fertilizerType;
    }

    @Override
    protected void executeTask(Robot robot, ITool tool) {
        if (fertilizerType == null || fertilizerType.isEmpty()) {
            System.out.println(robot.getRobotId() + ": ошибка - не указан тип удобрения");
            return;
        }
        System.out.println(robot.getRobotId() + ": внесение удобрения " + fertilizerType);
        tool.execute();
    }

    @Override
    public String toString() {
        return "Внесение удобрения '" + fertilizerType + "'";
    }
}