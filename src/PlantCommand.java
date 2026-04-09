public class PlantCommand extends TaskCommand {
    private final PlantType plantType;

    public PlantCommand(PlantType plantType) {
        super(ToolType.PLANTING, "Посадка");
        this.plantType = plantType;
    }

    @Override
    protected void executeTask(Robot robot, ITool tool) {
        if (plantType == null) {
            System.out.println(robot.getRobotId() + ": ошибка - не указан тип растения для посадки");
            return;
        }
        System.out.println(robot.getRobotId() + ": посадка " + plantType.getName());
        tool.execute();
    }

    @Override
    public String toString() {
        return "Посадка " + plantType.getName();
    }
}