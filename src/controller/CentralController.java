package controller;

import core.ICommand;
import core.IController;
import core.IRobot;
import core.ITool;
import models.EventType;
import models.RobotEvent;
import models.RobotStatus;
import models.ToolType;
import pool.GenericToolPool;
import pool.ToolPool;
import robot.Robot;
import visitor.RobotActivityVisitor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
import java.util.stream.Collectors;

//Класс центрального контроллера
public class CentralController implements IController {
    private HistoryManager historyManager = new HistoryManager();
    private ToolPool toolPool;
    private TaskHandler taskChain;
    private RobotActivityVisitor visitor = new RobotActivityVisitor();

    public RobotActivityVisitor getVisitor() {
        return visitor;
    }

    private void buildTaskChain() {
        if (robots.isEmpty()) {
            taskChain = null;
            return;
        }
        //Преобразуем коллекцию роботов в список для удобного доступа по индексу
        List<Robot> robotList = robots.values().stream()
                .map(r -> (Robot) r)
                .collect(Collectors.toList());
        RobotTaskHandler first = new RobotTaskHandler(robotList.get(0));
        RobotTaskHandler current = first;
        for (int i = 1; i < robotList.size(); i++) {
            RobotTaskHandler next = new RobotTaskHandler(robotList.get(i));
            current.setNext(next);
            current = next;
        }
        taskChain = first;
    }

    // Обновление цепочки после изменения списка роботов
    private void updateChain() {
        buildTaskChain();
    }

    public boolean executeCommand(ICommand command) {
        if (taskChain == null) {
            System.out.println("Нет доступных роботов для выполнения команды");
            return false;
        }
        boolean result = taskChain.handle(command, visitor);
        if (!result) {
            System.out.println("Команда не выполнена");
        }
        return result;
    }

    private class Snapshot {
        private final Map<String, IRobot> robotsSnapshot;
        private final ToolPool toolPoolSnapshot;

        Snapshot(Map<String, IRobot> robots, ToolPool toolPool) {
            //Глубокое копирование роботов (каждый робот клонируется)
            this.robotsSnapshot = new ConcurrentHashMap<>();
            for (Map.Entry<String, IRobot> entry : robots.entrySet()) {
                Robot original = (Robot) entry.getValue();
                this.robotsSnapshot.put(entry.getKey(), original.clone());
            }
            //Глубокое копирование пула инструментов
            this.toolPoolSnapshot = cloneToolPool(toolPool);
        }

        private ToolPool cloneToolPool(ToolPool pool) {
            GenericToolPool newPool = new GenericToolPool();
            for (ToolType type : ToolType.values()) {
                int count = pool.availableCount(type);
                for (int i = 0; i < count; i++) {
                    ITool tool = pool.acquireTool(type);
                    if (tool != null) {
                        newPool.addTool(tool.clone());
                        pool.releaseTool(tool);
                    }
                }
            }
            return newPool;
        }

        Map<String, IRobot> getRobotsSnapshot() {
            return Map.copyOf(robotsSnapshot);
        }

        ToolPool getToolPoolSnapshot() {
            return toolPoolSnapshot;
        }
    }

    private class HistoryManager {
        private final List<Snapshot> history = new ArrayList<>();
        private int current = -1;
        private boolean firstUndo = true;

        void push(Snapshot snapshot) {
            while (history.size() > current + 1) {
                history.remove(history.size() - 1);
            }
            history.add(snapshot);
            current = history.size() - 1;
            firstUndo = true;
        }

        Snapshot undo() {
            if (history.isEmpty()) return null;
            if (firstUndo) {
                firstUndo = false;
                return history.get(current); // первый раз возвращаем текущее состояние
            }
            if (current > 0) {
                current--;
                return history.get(current);
            }
            return history.get(current); //Если current == 0 (первый снимок), то больше откатываться некуда, возвращаем текущий (первый) снимок без изменения индекса
        }

        Snapshot redo() {
            if (history.isEmpty()) return null;
            if (current < history.size() - 1) {
                current++;
                firstUndo = true;
                return history.get(current);
            }
            return history.get(current);
        }
    }

    @Override
    public void onRobotEvent(RobotEvent event) {
        String id = event.getRobotId();
        EventType type = event.getEventType();
        Object data = event.getData();
        IRobot robot = robots.get(id); //ссылка на отправителя события

        switch (type) {
            case TASK_FAILED:
                System.out.printf("[Наблюдатель] Робот %s: не удалось выполнить команду. Перевожу в состояние ошибки%n", id);
                if (robot != null) robot.handleError();
                break;

            case ERROR:
                System.out.printf("[Наблюдатель] Робот %s в ошибке. Пытаюсь сбросить...%n", id);
                if (robot != null) robot.resetError();
                break;

            case TASK_COMPLETED:
                System.out.printf("[Наблюдатель] Робот %s выполнил задачу: %s%n", id, data);
                break;

            case TASK_STARTED:
                System.out.printf("[Наблюдатель] Робот %s начал выполнять задачу: %s%n", id, data);
                break;

            case CHARGING_STARTED:
                System.out.printf("[Наблюдатель] Робот %s заряжается%n", id);
                break;
            case MOVING_STARTED:
                System.out.printf("[Наблюдатель] Робот %s движется к %s%n", id, data);
                break;
            case ARRIVED:
                System.out.printf("[Наблюдатель] Робот %s прибыл в %s%n", id, data);
                break;
            case ERROR_RESET:
                System.out.printf("[Наблюдатель] Робот %s восстановлен после ошибки%n", id);
                break;
        }
    }

    private static class Holder {
        private static final CentralController INSTANCE = new CentralController();
    } //храним ссылку на самого себя

    public static CentralController getInstance() {
        return Holder.INSTANCE;
    }

    private Map<String, IRobot> robots = new LinkedHashMap<>();

    private CentralController() {
        toolPool = new GenericToolPool(); //пустой пул
        System.out.println("controller.CentralController: создан пустой пул инструментов");
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
        robot.addRobotObserver(this);
        updateChain();
    }

    public void assignCommand(String robotId, ICommand command) {
        IRobot robot = robots.get(robotId);
        if (robot != null) {
            System.out.println("Контроллер: назначение команды роботу " + robotId);
            command.execute((Robot) robot, visitor);   // передаём робота в команду
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

    public void saveToHistory() {
        Snapshot snap = createSnapshot(); // создаёт снимок текущего состояния
        historyManager.push(snap);
        System.out.println("Контроллер: состояние сохранено в историю");
    }

    // Откатить последнее изменение
    public boolean undo() {
        Snapshot previous = historyManager.undo();
        if (previous == null) {
            System.out.println("Контроллер: нечего откатывать");
            return false;
        }
        restore(previous);
        System.out.println("Контроллер: выполнен откат");
        return true;
    }

    // Вернуть отменённое действие
    public boolean redo() {
        Snapshot next = historyManager.redo();
        if (next == null) {
            System.out.println("Контроллер: нечего повторять");
            return false;
        }
        restore(next);
        System.out.println("Контроллер: повтор отменённого действия");
        return true;
    }

    // Вспомогательный метод – создаёт снимок текущего состояния
    private Snapshot createSnapshot() {
        return new Snapshot(robots, toolPool);
    }

    //Восстановление состояния из снимка
    private void restore(Snapshot snapshot) {
        this.robots.clear();
        this.robots.putAll(snapshot.getRobotsSnapshot());
        this.toolPool = snapshot.getToolPoolSnapshot();
        // Обновляем ссылку на пул у каждого робота
        for (IRobot robot : this.robots.values()) {
            robot.setToolPool(this.toolPool);
        }
    }

    public void removeRobot(String robotId) {
        IRobot robot = robots.get(robotId);
        if (robot != null) {
            robots.remove(robotId);
            robot.removeRobotObserver(this);
            System.out.println("Контроллер: робот " + robotId + " удалён");
            updateChain();
        }
    }

    public void clearRobots() {
        robots.forEach(((_, value) -> value.removeRobotObserver(this)));
        robots.clear();
        System.out.println("Контроллер: все роботы удалены");
        updateChain();
    }

}