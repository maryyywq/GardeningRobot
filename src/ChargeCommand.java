public class ChargeCommand implements ICommand {
    @Override
    public void execute(Robot robot) {
        robot.startCharging();
        robot.act();
    }

    @Override
    public boolean canBeHandledBy(Robot robot) {
        return true;
    }

    @Override
    public String toString() {
        return "Зарядка";
    }

    @Override
    public boolean isMadeOnThisSegment() { return false; }
}