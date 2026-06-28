package experts;

import core.IRobot;

abstract class RobotExpert {
    public abstract String getDescription();
    public abstract String check(IRobot robot);
}