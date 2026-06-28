package core;

import models.Location;
import models.MovementSystemType;
import models.MovementType;
import prototype.Prototype;

//Абстрактный класс системы передвижения
public abstract class IMovementSystem implements Prototype<IMovementSystem> {
    protected double speed;
    public abstract void moveTo(Location target); //Переместиться к точке
    protected abstract void stop(); //Остановиться
    protected abstract void setSpeed(double speed); //Установить скорость
    protected abstract MovementType getMovementType();
    public abstract MovementSystemType getSystemType();
    @Override public abstract IMovementSystem clone();
}
