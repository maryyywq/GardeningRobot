package core;

import models.RobotStatus;
import robot.Robot;

public interface IRobotState {
    void enter();
    void execute();
    void exit();
    RobotStatus getStatus();
    IRobotState clone(Robot newRobot);
}
