import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RobotGroup implements IRobot, Iterable<IRobot> {
    private String groupName;
    private List<IRobot> robots = new ArrayList<>();
    private ToolPool groupPool;

    public RobotGroup(String groupName) {
        this.groupName = groupName;
    }
    @Override
    public void addRobotObserver(IRobotObserver observer) {
        throw new UnsupportedOperationException("RobotGroup не поддерживает добавление наблюдателей");
    }

    @Override
    public void removeRobotObserver(IRobotObserver observer) {
        throw new UnsupportedOperationException("RobotGroup не поддерживает удаление наблюдателей");
    }

    @Override
    public void notifyRobotObservers(RobotEvent event) {
    }

    @Override
    public void handleError() {
        System.out.println("Группа " + groupName + ": обработка ошибки для всех роботов");
        for (IRobot robot : robots) {
            robot.handleError();
        }
    }

    @Override
    public void resetError() {
        System.out.println("Группа " + groupName + ": сброс ошибок у всех роботов");
        for (IRobot robot : robots) {
            robot.resetError();
        }
    }

    @Override
    public IMovementSystem getMovementSystem() {
        throw new UnsupportedOperationException("Группа не имеет единой системы передвижения");
    }

    public void addRobot(IRobot robot) {
        robots.add(robot);
        // Если группе уже назначен пул, сразу передаём его новому роботу
        if (groupPool != null) {
            robot.setToolPool(groupPool);
        }
    }

    @Override
    public void setToolPool(ToolPool pool) {
        this.groupPool = pool;
        for (IRobot robot : robots) {
            robot.setToolPool(pool);
        }
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
