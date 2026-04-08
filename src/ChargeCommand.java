public class ChargeCommand implements ICommand {
    private final Robot robot;

    public ChargeCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.startCharging();
        robot.act();
    }
}
