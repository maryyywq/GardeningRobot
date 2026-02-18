public interface IRobot {
    void startTask();
    void stopTask();
    RobotStatus getStatus();
    void setTool(ITool tool);
    void receiveCommand(String command);
    boolean canUseTool(ITool tool);
}
