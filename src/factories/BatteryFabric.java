package factories;

import components.power.Battery;
import core.IPowerSource;

public class BatteryFabric implements PowerSourceFabric {
    @Override
    public IPowerSource create() {
        return new Battery();
    }
}
