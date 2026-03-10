//Класс топливного элемента как источника питания роботов
public class FuelCell extends IPowerSource {
    public FuelCell() {level = 100.0;} //Уровень заряда в процентах
    @Override public void charge() { level = 100.0; System.out.println("Источник питания на основе топлива: заправка завершена"); }
    @Override public void switchToBackup() { System.out.println("Источник питания на основе топлива: переход на резервный бак");
    setLevel(100);}
    @Override public boolean hasBackup() {
        return true;
    }

    @Override public void consume(double amount) {
        if (amount <= level) {
            level -= amount;
            System.out.printf("Топливный элемент: потреблено %.1f ед., осталось %.1f%%\n", amount, level);
        } else {
            System.out.println("Топливный элемент: недостаточно топлива!");
        }
    }
}
