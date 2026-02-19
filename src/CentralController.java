import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

public class CentralController implements IController {
    private Map<String, IRobot> robots = new ConcurrentHashMap<>();

    public void registerRobot(String id, IRobot robot) {
        robots.put(id, robot);
    }

    public void assignTask(String robotId, Task task) {
        IRobot robot = robots.get(robotId);
        if (robot != null) {
            System.out.println("Контроллер: назначение задачи " + task.type + " роботу " + robotId);
            // Передаём команду (строку) роботу, не вызываем startTask отдельно
            robot.receiveCommand(task.type);
        } else {
            System.out.println("Контроллер: робот " + robotId + " не найден");
        }
    }

    @Override
    public Map<String, RobotStatus> monitorRobots() {
        Map<String, RobotStatus> statusMap = new HashMap<>();
        for (Map.Entry<String, IRobot> entry : robots.entrySet()) {
            statusMap.put(entry.getKey(), entry.getValue().getStatus());
        }
        return statusMap;
    }
}
