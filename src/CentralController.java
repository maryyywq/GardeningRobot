import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

//Класс центрального контроллера
public class CentralController implements IController {

    private Map<String, IRobot> robots = new ConcurrentHashMap<>(); //Словарь роботов

    //Добавление роботов в систему
    public void registerRobot(String id, IRobot robot) {
        robots.put(id, robot);
    }

    //Hазначает задачу роботу
    public void assignTask(String robotId, Task task) {
        IRobot robot = robots.get(robotId);
        if (robot != null) {
            System.out.println("Контроллер: назначение задачи " + task.type + " роботу " + robotId);
            //Передаем команду роботу
            robot.receiveCommand(task.type);
        } else {
            System.out.println("Контроллер: робот " + robotId + " не найден");
        }
    }

    @Override
    public Map<String, RobotStatus> monitorRobots() {
        //Возвращает текущие статусы всех зарегистрированных роботов
        Map<String, RobotStatus> statusMap = new HashMap<>();
        for (Map.Entry<String, IRobot> entry : robots.entrySet()) {
            statusMap.put(entry.getKey(), entry.getValue().getStatus());
        }
        return statusMap;
    }
}
