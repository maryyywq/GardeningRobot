//Интерфейс навигации
interface INavigation {
    void updatePosition(); //Обновить текущие координаты
    Route planRoute(Location start, Location goal); //Построить оптимальный маршрут от старта до цели
    void adjustRoute(Obstacle obstacle); //Скорректировать маршрут при обнаружении препятствия
}
