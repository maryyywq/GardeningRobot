class MaxPerformanceManager extends PowerManager {
    public MaxPerformanceManager(IPowerSource ps) {
        super(ps);
    }

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
}