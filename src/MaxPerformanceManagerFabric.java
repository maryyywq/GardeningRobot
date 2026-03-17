public class MaxPerformanceManagerFabric implements PowerManagerFabric {
    @Override
    public PowerManager create(IPowerSource powerSource) {
        return new MaxPerformanceManager(powerSource);
    }
}
