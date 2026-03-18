class BalancePowerManager extends PowerManager {
    public BalancePowerManager(IPowerSource ps) {
        super(ps);
    }
    public BalancePowerManager(BalancePowerManager other) { super(other.powerSource.clone()); }

    @Override
    public PowerAction checkPower(double requiredEnergy) {
        if (getLevel() - requiredEnergy >= 10.0) {
            return PowerAction.CONTINUE;
        } else {
            if (powerSource.hasBackup()) {
                return PowerAction.USE_BACKUP;
            } else {
                return PowerAction.CHARGE;
            }
        }
    }
    @Override public PowerManager clone() { return new BalancePowerManager(this); }
    @Override
    public String toString() {
        return "MaxPerformanceManager (" + powerSource.toString() + ")";
    }
}