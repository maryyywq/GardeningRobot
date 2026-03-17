import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RobotGroup implements IRobot, Iterable<IRobot> {
    private String groupName;
    private List<IRobot> robots = new ArrayList<>();

    public RobotGroup(String groupName) {
        this.groupName = groupName;
    }
    @Override
    public IMovementSystem getMovementSystem() {
        throw new UnsupportedOperationException("Группа не имеет единой системы передвижения");
    }

    public void addRobot(IRobot robot) {
        robots.add(robot);
    }

    public void removeRobot(IRobot robot) {
        robots.remove(robot);
    }

    public List<IRobot> getRobots() {
        return robots;
    }

    @Override
    public Iterator<IRobot> iterator() {
        return new RobotGroupIterator(this);
    }

    @Override
    public void startTask() {
        System.out.println("Группа " + groupName + ": запуск задач");
        for (IRobot robot : robots) {
            robot.startTask();
        }
    }

    @Override
    public void stopTask() {
        System.out.println("Группа " + groupName + ": остановка задач");
        for (IRobot robot : robots) {
            robot.stopTask();
        }
    }

    @Override
    public RobotStatus getStatus() {
        //Возвращаем WORKING, если хотя бы один робот работает
        for (IRobot robot : robots) {
            if (robot.getStatus() == RobotStatus.WORKING) {
                return RobotStatus.WORKING;
            }
        }
        return RobotStatus.IDLE;
    }

    @Override
    public void setTool(ITool tool) {
        for (IRobot robot : robots) {
            robot.setTool(tool);
        }
    }

    @Override
    public void receiveCommand(String command) {
        for (IRobot robot : robots) {
            robot.receiveCommand(command);
        }
    }

    @Override
    public boolean canUseTool(ITool tool) {
        for (IRobot robot : robots) {
            if (!robot.canUseTool(tool)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        return String.format("Группа '%s' [количество участников: %d]", groupName, robots.size());
    }

    @Override
    public ITool getCurrentTool() {
        throw new UnsupportedOperationException("Группа роботов не имеет единого текущего инструмента");
    }

    @Override
    public String getRobotId() {
        throw new UnsupportedOperationException("Группа не имеет единого идентификатора");
    }
}
