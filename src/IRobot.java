interface IRobot {
    void startTask();
    void stopTask();
    RobotStatus getStatus();
    void setTool(ITool tool);
    void receiveCommand(String command);
    boolean canUseTool(ITool tool);
    IMovementSystem getMovementSystem();
    INavigation getNavigation();
    IPowerSource getPowerSource();
    ICommunication getCommunication();
    IKnowledgeBase<?> getKnowledgeBase();
    ITool getCurrentTool();
}
