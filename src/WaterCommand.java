public class WaterCommand extends TaskCommand {
    private final double volume;

    public WaterCommand(Robot robot, double volume) {
        super(robot, ToolType.WATERING, "Полив");
        this.volume = volume;
    }

    @Override
    protected void executeTask(ITool tool) {
        if (volume <= 0) {
            System.out.println(robot.getRobotId() + ": ошибка - объём полива должен быть положительным");
            return;
        }
        System.out.println(robot.getRobotId() + ": полив с объёмом " + volume + " л");
        tool.execute();
    }
}