public class TreatCommand extends TaskCommand {
    private final String medicine;

    public TreatCommand(Robot robot, String medicine) {
        super(robot, ToolType.MEDICAL, "Лечение");
        this.medicine = medicine;
    }

    @Override
    protected void executeTask(ITool tool) {
        if (medicine == null || medicine.isEmpty()) {
            System.out.println(robot.getRobotId() + ": ошибка - не указан препарат для лечения");
            return;
        }
        System.out.println(robot.getRobotId() + ": лечение препаратом " + medicine);
        tool.execute();
    }
}