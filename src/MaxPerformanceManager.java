class MaxPerformanceManager extends PowerManager {
    public MaxPerformanceManager(IPowerSource ps) {
        super(ps);
    }
    public MaxPerformanceManager(MaxPerformanceManager other) { super(other.powerSource.clone()); }

    @Override
    public PowerAction checkPower(double requiredEnergy) {
        if (getLevel() - requiredEnergy >= 5.0) {
            return PowerAction.CONTINUE;
        } else {
            if (powerSource.hasBackup()) {
                return PowerAction.USE_BACKUP;
            } else {
                return PowerAction.STOP;
            }
        }
    }
    @Override public PowerManager clone() { return new MaxPerformanceManager(this); }
    @Override
    public String toString() {
        return "MaxPerformanceManager (" + powerSource.toString() + ")";
    }
}