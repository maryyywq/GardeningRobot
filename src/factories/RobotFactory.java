package factories;

import map.MapSegmentFactory;
import models.Location;
import robot.Robot;

public interface RobotFactory {
    Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory);
}
