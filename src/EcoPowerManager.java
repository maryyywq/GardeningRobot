class EcoPowerManager extends PowerManager {
    public EcoPowerManager(IPowerSource ps) {
        super(ps);
    }
    public EcoPowerManager(EcoPowerManager other) { super(other.powerSource.clone()); }
    @Override
    public PowerAction checkPower(double requiredEnergy) {
        if (getLevel() - requiredEnergy >= 20.0) {
            return PowerAction.CONTINUE;
        } else {
            return PowerAction.CHARGE;
        }
    }
    @Override public PowerManager clone() { return new EcoPowerManager(this); }
}
