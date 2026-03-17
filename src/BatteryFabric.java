public class BatteryFabric implements PowerSourceFabric {
    @Override
    public IPowerSource create() {
        return new Battery();
    }
}
