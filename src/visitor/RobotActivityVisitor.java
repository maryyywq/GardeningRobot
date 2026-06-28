package visitor;

import core.ICommand;
import core.IMapSegmentVisitor;
import core.IRobot;
import map.MapSegment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class RobotActivityVisitor implements IMapSegmentVisitor {
    private final Map<MapSegment, AtomicInteger> visitCount = new ConcurrentHashMap<>();
    private final Map<MapSegment, List<String>> visitsLog = new ConcurrentHashMap<>();
    private final Map<MapSegment, AtomicInteger> actionCount = new ConcurrentHashMap<>();
    private final Map<MapSegment, List<String>> actionsLog = new ConcurrentHashMap<>();

    @Override
    public void visit(MapSegment segment, IRobot robot, ICommand command) {
        if (command.isMadeOnThisSegment()) {
            actionCount.computeIfAbsent(segment, k -> new AtomicInteger()).incrementAndGet();//если в сегменте еще никого не было
            String desc = robot.getRobotId() + ": " + command.toString();
            actionsLog.computeIfAbsent(segment, k -> new ArrayList<>()).add(desc);
        } else {
            visitCount.computeIfAbsent(segment, k -> new AtomicInteger()).incrementAndGet();
            String desc = robot.getRobotId() + ": " + command.toString();
            visitsLog.computeIfAbsent(segment, k -> new ArrayList<>()).add(desc);
        }
    }

    public void printStatistics() {
        System.out.println("Посещения (перемещения):");
        for (var entry : visitCount.entrySet()) {
            MapSegment seg = entry.getKey();
            System.out.println("Сегмент: " + seg);
            System.out.println("  Посещений: " + entry.getValue().get());
            List<String> logs = visitsLog.get(seg);
            if (logs != null && !logs.isEmpty()) {
                System.out.println("  Действия:");
                for (String act : logs) System.out.println("    - " + act);
            }
        }
        System.out.println("Действия:");
        for (var entry : actionCount.entrySet()) {
            MapSegment seg = entry.getKey();
            System.out.println("Сегмент: " + seg);
            System.out.println("  Выполнено действий: " + entry.getValue().get());
            List<String> logs = actionsLog.get(seg);
            if (logs != null && !logs.isEmpty()) {
                System.out.println("  Список действий:");
                for (String act : logs) System.out.println("    - " + act);
            }
        }
    }
}