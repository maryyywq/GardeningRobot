public class MoveCommand implements ICommand {
    private final Robot robot;
    private final Location destination;

    public MoveCommand(Robot robot, Location destination) {
        this.robot = robot;
        this.destination = destination;
    }

    @Override
    public void execute() {
        robot.startMoving(destination);
        robot.act();
    }
}