package factories;

import core.IPowerSource;

public interface PowerSourceFabric {
    public abstract IPowerSource create();
}
