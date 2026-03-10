//Класс аккумулятора как источника питания робота
public class Battery extends IPowerSource {
    public Battery() {level = 100.0;} //Уровень заряда в процентах
    @Override public void charge() { level = 100.0; System.out.println("Аккумулятор: зарядка завершена"); } //Зарядка до 100%
    @Override public double getLevel() { return level; }
    @Override public void switchToBackup() { System.out.println("Аккумулятор: резервный источник отсутствует"); }
    @Override
    public String toString() {
        return String.format("Аккумулятор (заряд: %.1f%%)", level);
    }
    @Override public boolean hasBackup() {
        return false;
    }
    @Override public void consume(double amount) {
        if (amount <= level) {
            level -= amount;
            System.out.printf("Аккумулятор: потреблено %.1f ед., осталось %.1f%%\n", amount, level);
        } else {
            System.out.println("Аккумулятор: недостаточно энергии!");
        }
    }
}

