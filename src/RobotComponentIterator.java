import java.util.Iterator;
import java.util.NoSuchElementException;

class RobotComponentIterator implements Iterator<Object> {
    private Robot robot;
    private int currentIndex = 0;
    private static final int TOTAL_COMPONENTS = 6;

    public RobotComponentIterator(Robot robot) {
        this.robot = robot;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < TOTAL_COMPONENTS;
    }

    @Override
    public Object next() {
        if (!hasNext()) throw new NoSuchElementException();
        Object component;
        switch (currentIndex) {
            case 0: component = robot.getMovementSystem(); break;
            case 1: component = robot.getNavigation(); break;
            case 2: component = robot.getPowerManager(); break;
            case 3: component = robot.getCommunication(); break;
            case 4: component = robot.getKnowledgeBase(); break;
            case 5: component = robot.getCurrentTool(); break;
            default: throw new NoSuchElementException();
        }
        currentIndex++;
        return component;
    }
}