import java.util.*;

//Реализация GPS-навигации
public class GPSNavigation extends INavigation {
    @Override public void updatePosition() { //Обновление позиции
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
