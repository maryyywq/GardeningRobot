package factories;

import components.power.BalancePowerManager;
import components.power.PowerManager;
import core.IPowerSource;

public class BalancePowerManagerFabric implements PowerManagerFabric {
    @Override
    public PowerManager create(IPowerSource powerSource) {
        return new BalancePowerManager(powerSource);
    }
}
