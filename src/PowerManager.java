abstract public class PowerManager {
    protected IPowerSource powerSource;

    public PowerManager(IPowerSource powerSource) {
        this.powerSource = powerSource;
    }

    public double getLevel() {
        return powerSource.getLevel();
    }

    //Проверка возможности выполнения задачи с требуемым расходом энергии
    public abstract PowerAction checkPower(double requiredEnergy);

    //Делегирование зарядки источнику
    public void charge() {
        powerSource.charge();
    }

    public void switchToBackup() {
        if (powerSource.hasBackup()) {
            powerSource.switchToBackup();
        }
    }
}
