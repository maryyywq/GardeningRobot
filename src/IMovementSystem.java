//Абстрактный класс системы передвижения
abstract class IMovementSystem {
    protected double speed;
    abstract void moveTo(Location target); //Переместиться к точке
    abstract void stop(); //Остановиться
    abstract void setSpeed(double speed); //Установить скорость
    abstract MovementType getMovementType(); //Получить тип движения (GROUND/AIR)
}
