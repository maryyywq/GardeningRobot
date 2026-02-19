//Абстрактный класс навигации
abstract class INavigation {
    protected Location current = new Location(0, 0);
    abstract void updatePosition(); //Обновить текущие координаты
    abstract Route planRoute(Location start, Location goal); //Построить оптимальный маршрут от старта до цели
    abstract void adjustRoute(Obstacle obstacle); //Скорректировать маршрут при обнаружении препятствия
}
