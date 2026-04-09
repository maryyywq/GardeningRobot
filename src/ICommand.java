public interface ICommand {
    void execute(Robot robot);
    default void execute(Robot robot, IMapSegmentVisitor visitor) {
        execute(robot);
        robot.getCurrentSegment().accept(visitor, robot, this);
    }
    boolean canBeHandledBy(Robot robot);
    boolean isMadeOnThisSegment();
}
