package core;

import models.RobotEvent;

public interface IRobotObserver {
    void onRobotEvent(RobotEvent event);
}
