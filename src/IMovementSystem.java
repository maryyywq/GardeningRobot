//Интерфейс системы передвижения
interface IMovementSystem {
    void moveTo(Location target); //Переместиться к точке
    void stop(); //Остановиться
    void setSpeed(double speed); //Установить скорость
    MovementType getMovementType(); //Получить тип движения (GROUND/AIR)
}
