import java.util.*;

public class GPSNavigation implements INavigation {
    private Location current = new Location(0, 0);
    @Override public void updatePosition() {
        System.out.println("GPS-навигация: обновление позиции по GPS");
    }
    @Override public Route planRoute(Location start, Location goal) {
        System.out.println("GPS-навигация: планирование маршрута от " + start + " до " + goal);
        return new Route(Arrays.asList(start, goal));
    }
    @Override public void adjustRoute(Obstacle obstacle) {
        System.out.println("GPS-навигация: корректировка маршрута для обхода препятствия в " + obstacle.position);
    }
}
