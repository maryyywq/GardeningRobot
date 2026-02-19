import java.util.*;

public class RobotProxy implements IRobot {
    private IRobot realRobot;
    private Set<String> allowedCommands;
    private static final double MIN_POWER = 10.0;

    public RobotProxy(IRobot realRobot) {
        this.realRobot = realRobot;
        this.allowedCommands = new HashSet<>(Arrays.asList("MOVE", "SCAN", "STATUS"));
    }

    // Проверка всех систем перед запуском задачи
    private boolean canStart() {
        System.out.println("Proxy: проверка систем...");

        // Проверка наличия всех компонентов через геттеры
        if (realRobot.getMovementSystem() == null) {
            System.out.println("Proxy: ошибка - отсутствует система передвижения");
            return false;
        }
        if (realRobot.getNavigation() == null) {
            System.out.println("Proxy: ошибка - отсутствует навигационная система");
            return false;
        }
        if (realRobot.getPowerSource() == null) {
            System.out.println("Proxy: ошибка - отсутствует источник питания");
            return false;
        }
        if (realRobot.getCommunication() == null) {
            System.out.println("Proxy: ошибка - отсутствует система связи");
            return false;
        }
        if (realRobot.getKnowledgeBase() == null) {
            System.out.println("Proxy: ошибка - отсутствует база знаний");
            return false;
        }

        // Проверка уровня заряда
        double level = realRobot.getPowerSource().getLevel();
        if (level < MIN_POWER) {
            System.out.printf("Proxy: ошибка - низкий уровень заряда/топлива: %.1f%% (минимум %.1f%%)%n", level, MIN_POWER);
            return false;
        }

        // Проверка инструмента
        ITool tool = realRobot.getCurrentTool();
        if (tool == null) {
            System.out.println("Proxy: ошибка - инструмент не установлен");
            return false;
        }
        if (tool.getStatus() == ToolStatus.ERROR) {
            System.out.println("Proxy: ошибка - инструмент в состоянии ошибки");
            return false;
        }
        if (!realRobot.canUseTool(tool)) {
            System.out.println("Proxy: ошибка - инструмент несовместим с базой знаний");
            return false;
        }

        System.out.println("Proxy: все системы в норме");
        return true;
    }

    @Override
    public void startTask() {
        System.out.println("Proxy: запрос на запуск задачи");
        if (canStart()) {
            System.out.println("Proxy: запускаю задачу");
            realRobot.startTask();
        } else {
            System.out.println("Proxy: задача не запущена из-за проблем с системами");
        }
    }

    @Override
    public void stopTask() {
        System.out.println("Proxy: логирование stopTask");
        realRobot.stopTask();
    }

    @Override
    public RobotStatus getStatus() {
        System.out.println("Proxy: получение статуса");
        return realRobot.getStatus();
    }

    @Override
    public void setTool(ITool tool) {
        System.out.println("Proxy: проверка совместимости инструмента " + tool.getClass().getSimpleName());
        if (realRobot.canUseTool(tool)) {
            realRobot.setTool(tool);
        } else {
            System.out.println("Proxy: инструмент " + tool.getClass().getSimpleName() + " несовместим с этим роботом. Отказ.");
        }
    }

    @Override
    public void receiveCommand(String command) {
        String cmd = command.split(" ")[0];
        if (allowedCommands.contains(cmd)) {
            System.out.println("Proxy: команда '" + command + "' разрешена");
            realRobot.receiveCommand(command);
        } else {
            System.out.println("Proxy: команда '" + command + "' заблокирована (не разрешена)");
        }
    }

    @Override
    public boolean canUseTool(ITool tool) {
        return realRobot.canUseTool(tool);
    }

    // Реализация геттеров (делегирование)
    @Override public IMovementSystem getMovementSystem() { return realRobot.getMovementSystem(); }
    @Override public INavigation getNavigation() { return realRobot.getNavigation(); }
    @Override public IPowerSource getPowerSource() { return realRobot.getPowerSource(); }
    @Override public ICommunication getCommunication() { return realRobot.getCommunication(); }
    @Override public IKnowledgeBase<?> getKnowledgeBase() { return realRobot.getKnowledgeBase(); }
    @Override public ITool getCurrentTool() { return realRobot.getCurrentTool(); }
}
