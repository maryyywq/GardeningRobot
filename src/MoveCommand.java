public class MoveCommand implements ICommand {
    private final Location destination;

    public MoveCommand(Location destination) {
        this.destination = destination;
    }

    @Override
    public void execute(Robot robot) {
        robot.startMoving(destination);
        robot.act();
    }

    @Override
    public boolean canBeHandledBy(Robot robot) {
        return true;
    }
}