package factories;

import core.ITool;
import tools.WateringTool;

public class WateringToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new WateringTool();
    }
}
