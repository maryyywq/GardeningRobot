//Интерфейс навигации
interface INavigation {
    void updatePosition();
    Route planRoute(Location start, Location goal);
    void adjustRoute(Obstacle obstacle);
}
