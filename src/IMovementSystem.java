//Абстрактный класс системы передвижения
abstract class IMovementSystem implements Prototype<IMovementSystem> {
    protected double speed;
    abstract void moveTo(Location target); //Переместиться к точке
    abstract void stop(); //Остановиться
    abstract void setSpeed(double speed); //Установить скорость
    abstract MovementType getMovementType();
    abstract MovementSystemType getSystemType();
    @Override public abstract IMovementSystem clone();
}
