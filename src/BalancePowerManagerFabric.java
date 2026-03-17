public class BalancePowerManagerFabric implements PowerManagerFabric {
    @Override
    public PowerManager create(IPowerSource powerSource) {
        return new BalancePowerManager(powerSource);
    }
}
