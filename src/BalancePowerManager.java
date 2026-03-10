class BalancePowerManager extends PowerManager {
    public BalancePowerManager(IPowerSource ps) {
        super(ps);
    }

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
}