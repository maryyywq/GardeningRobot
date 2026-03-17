public interface RobotFactory {
    Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory);
}
