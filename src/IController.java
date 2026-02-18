public interface IController {
    void assignTask(String robotId, Task task);
    Map<String, RobotStatus> monitorRobots();
}
