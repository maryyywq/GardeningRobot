//Абстрактный класс источника питания
abstract class IPowerSource {
    protected double level;
    abstract void charge(); //Запустить процесс зарядки/заправки
    abstract void switchToBackup();//Переключиться на резервный источник питания
    abstract boolean hasBackup();
    public double getLevel() { return level; }
    public void setLevel(double level) { this.level = level; }
    abstract void consume(double amount);
    @Override public abstract IPowerSource clone();
}
