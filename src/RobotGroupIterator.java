import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class RobotGroupIterator implements Iterator<IRobot> {
    private List<IRobot> robots;
    private int index = 0;

    public RobotGroupIterator(RobotGroup group) {
        this.robots = group.getRobots();
    }

    @Override
    public boolean hasNext() {
        return index < robots.size();
    }

    @Override
    public IRobot next() {
        if (!hasNext()) throw new NoSuchElementException();
        return robots.get(index++);
    }
}