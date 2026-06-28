package factories;

import components.power.PowerManager;
import core.IPowerSource;

public interface PowerManagerFabric {
    public abstract PowerManager create(IPowerSource powerSource);
}
