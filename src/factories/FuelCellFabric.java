package factories;

import components.power.FuelCell;
import core.IPowerSource;

public class FuelCellFabric implements PowerSourceFabric {
    @Override
    public IPowerSource create() {
        return new FuelCell();
    }
}
