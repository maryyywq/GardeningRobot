//Абстрактный класс источника питания
abstract class IPowerSource {
    protected double level;
    abstract void charge(); //Запустить процесс зарядки/заправки
    abstract double getLevel(); //Получить текущий уровень заряда/топлива (в процентах)
    abstract void switchToBackup(); //Переключиться на резервный источник питания
}
