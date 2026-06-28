package core;

//Абстрактный класс источника питания
public abstract class IPowerSource {
    protected double level;
    public abstract void charge(); //Запустить процесс зарядки/заправки
    public abstract void switchToBackup();//Переключиться на резервный источник питания
    public abstract boolean hasBackup();
    public double getLevel() { return level; }
    public void setLevel(double level) { this.level = level; }
    public abstract void consume(double amount);
    @Override public abstract IPowerSource clone();
}
