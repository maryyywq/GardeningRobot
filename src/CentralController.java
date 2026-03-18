import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

//Класс центрального контроллера
public class CentralController implements IController {

    private ToolPool toolPool;

    private static class Holder {
        private static final CentralController INSTANCE = new CentralController();
    }

    public static CentralController getInstance() {
        return Holder.INSTANCE;
    }

    private Map<String, IRobot> robots = new ConcurrentHashMap<>();//Словарь роботов

    private CentralController() {
        toolPool = new GenericToolPool(); // пустой пул
        System.out.println("CentralController: создан пустой пул инструментов");
    }

    public void addToolToPool(ITool tool) {
        toolPool.addTool(tool);
        System.out.println("Контроллер: инструмент " + tool + " добавлен в пул");
    }

    public void removeToolFromPool(ITool tool) {
        if (toolPool.removeTool(tool)) {
            System.out.println("Контроллер: инструмент " + tool + " удалён из пула");
        }
    }


    public void registerRobot(IRobot robot) {
        String id = robot.getRobotId();
        if (id == null) {
            throw new IllegalArgumentException("Робот не имеет идентификатора");
        }
        robots.put(id, robot);
        robot.setToolPool(toolPool); // передаём ссылку на пул
        System.out.println("Контроллер: робот " + id + " зарегистрирован и получил доступ к пулу инструментов");
    }

    public void assignTask(String robotId, Task task) {
        IRobot robot = robots.get(robotId);
        if (robot != null) {
            System.out.println("Контроллер: назначение задачи " + task.type + " роботу " + robotId);
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

    public List<IRobot> getAllRobots() {
        return new ArrayList<>(robots.values());
    }

    public IRobot findRobotWithTool(ToolType toolType) {
        for (IRobot robot : robots.values()) {
            ITool tool = robot.getCurrentTool();
            if (tool != null && tool.getToolType() == toolType) {
                return robot;
            }
        }
        return null;
    }

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