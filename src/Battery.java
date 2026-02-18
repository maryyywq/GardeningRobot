public class Battery implements IPowerSource {
    private double level = 100.0;
    @Override public void charge() { level = 100.0; System.out.println("Аккумулятор: зарядка завершена"); }
    @Override public double getLevel() { return level; }
    @Override public void switchToBackup() { System.out.println("Аккумулятор: резервный источник отсутствует"); }
}
