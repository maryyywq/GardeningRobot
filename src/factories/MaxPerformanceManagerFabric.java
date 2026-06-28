package factories;

import components.power.MaxPerformanceManager;
import components.power.PowerManager;
import core.IPowerSource;

public class MaxPerformanceManagerFabric implements PowerManagerFabric {
    @Override
    public PowerManager create(IPowerSource powerSource) {
        return new MaxPerformanceManager(powerSource);
    }
}
