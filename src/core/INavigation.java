package core;

import models.Location;
import models.Obstacle;
import models.Route;
import prototype.Prototype;

//Абстрактный класс навигации
public abstract class INavigation implements Prototype<INavigation> {
    protected Location current = new Location(0, 0);
    protected abstract void updatePosition(); //Обновить текущие координаты
    protected abstract Route planRoute(Location start, Location goal); //Построить оптимальный маршрут от старта до цели
    protected abstract void adjustRoute(Obstacle obstacle); //Скорректировать маршрут при обнаружении препятствия
    public void setCurrentLocation(Location loc) {
        this.current = loc;
    }
    @Override public abstract INavigation clone();
}
