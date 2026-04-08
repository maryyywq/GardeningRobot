public class PlantCommand extends TaskCommand {
    private final PlantType plantType;

    public PlantCommand(Robot robot, PlantType plantType) {
        super(robot, ToolType.PLANTING, "Посадка");
        this.plantType = plantType;
    }

    @Override
    protected void executeTask(ITool tool) {
        if (plantType == null) {
            System.out.println(robot.getRobotId() + ": ошибка - не указан тип растения для посадки");
            return;
        }
        System.out.println(robot.getRobotId() + ": посадка " + plantType.getName());
        tool.execute();
    }
}