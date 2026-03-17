public class EcoPowerManagerFabric implements PowerManagerFabric {
    @Override
    public PowerManager create(IPowerSource powerSource) {
        return new EcoPowerManager(powerSource);
    }
}
