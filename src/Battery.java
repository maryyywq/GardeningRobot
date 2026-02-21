//Класс аккумулятора как источника питания робота
public class Battery extends IPowerSource {
    public Battery() {level = 100.0;} //Уровень заряда в процентах
    @Override public void charge() { level = 100.0; System.out.println("Аккумулятор: зарядка завершена"); } //Зарядка до 100%
    @Override public double getLevel() { return level; }
    @Override public void switchToBackup() { System.out.println("Аккумулятор: резервный источник отсутствует"); }
    public void setLevel(double level) { this.level = level; }
}
