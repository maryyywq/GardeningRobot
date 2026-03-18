import java.util.*;

//Реализация GPS-навигации
public class GPSNavigation extends INavigation {
    public GPSNavigation() { }
    public GPSNavigation(GPSNavigation other) { this.current = other.current.clone(); }
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
    @Override
    public String toString() {
        return "GPS-навигация (текущая позиция: " + current + ")";
    }

    @Override public INavigation clone() { return new GPSNavigation(this); }

}
