abstract public class PowerManager implements Prototype<PowerManager>{
    protected IPowerSource powerSource;
    public PowerManager(IPowerSource powerSource) {
        this.powerSource = powerSource;
    }
    @Override public abstract PowerManager clone();

    public double getLevel() {
        return powerSource.getLevel();
    }

    public void consumeEnergy(double amount) { powerSource.consume(amount); } //использовать энергию

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
