public interface IRobotObservable {
    void addRobotObserver(IRobotObserver observer);
    void removeRobotObserver(IRobotObserver observer);
    void notifyRobotObservers(RobotEvent event);
}
