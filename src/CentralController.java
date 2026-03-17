import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

//Класс центрального контроллера
public class CentralController implements IController {

    private static class Holder {
        private static final CentralController INSTANCE = new CentralController();
    }
    private CentralController() {}

    public static CentralController getInstance() {
        return Holder.INSTANCE;
    }

    private Map<String, IRobot> robots = new ConcurrentHashMap<>(); //Словарь роботов

    public void registerRobot(IRobot robot) {
        String id = robot.getRobotId();
        if (id == null) {
            throw new IllegalArgumentException("Робот не имеет идентификатора");
        }
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

    //получить всех роботов
    public List<IRobot> getAllRobots() {
        return new ArrayList<>(robots.values());
    }

    // Новый метод: найти первого робота с заданным типом инструмента
    public IRobot findRobotWithTool(ToolType toolType) {
        for (IRobot robot : robots.values()) {
            ITool tool = robot.getCurrentTool();
            if (tool != null && tool.getToolType() == toolType) {
                return robot;
            }
        }
        return null;
    }

    // Новый метод: найти всех роботов с заданным типом инструмента
    public List<IRobot> findAllRobotsWithTool(ToolType toolType) {
        List<IRobot> result = new ArrayList<>();
        for (IRobot robot : robots.values()) {
            ITool tool = robot.getCurrentTool();
            if (tool != null && tool.getToolType() == toolType) {
                result.add(robot);
            }
        }
        return result;
    }

}
