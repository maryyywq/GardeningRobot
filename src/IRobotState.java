public interface IRobotState {
    void enter();
    void execute();
    void exit();
    RobotStatus getStatus();
    IRobotState clone(Robot newRobot);
}
