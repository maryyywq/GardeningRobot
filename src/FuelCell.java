//Класс топливного элемента как источника питания роботов
public class FuelCell implements IPowerSource {
    private double level = 100.0;
    @Override public void charge() { level = 100.0; System.out.println("Источник питания на основе топлива: заправка завершена"); }
    @Override public double getLevel() { return level; }
    @Override public void switchToBackup() { System.out.println("Источник питания на основе топлива: переход на резервный бак"); }
}
