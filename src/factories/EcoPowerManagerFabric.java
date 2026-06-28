package factories;

import components.power.EcoPowerManager;
import components.power.PowerManager;
import core.IPowerSource;

public class EcoPowerManagerFabric implements PowerManagerFabric {
    @Override
    public PowerManager create(IPowerSource powerSource) {
        return new EcoPowerManager(powerSource);
    }
}
