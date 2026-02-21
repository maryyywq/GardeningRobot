import java.util.*;

public class VisionNavigation extends INavigation {
    @Override public void updatePosition() {
        System.out.println("Навигация с помощью компьютерного зрения: обновление позиции по камерам");
    }
    @Override public Route planRoute(Location start, Location goal) {
        System.out.println("Навигация с помощью компьютерного зрения: планирование маршрута от " + start + " до " + goal + " на основе изображений");
        return new Route(Arrays.asList(start, goal));
    }
    @Override public void adjustRoute(Obstacle obstacle) {
        System.out.println("Навигация с помощью компьютерного зрения: обнаружено препятствие в " + obstacle.position + ", пересчёт маршрута");
    }

}
