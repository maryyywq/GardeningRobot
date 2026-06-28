package factories;

import core.ITool;
import tools.HarvestingTool;

public class HarvestingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new HarvestingTool();
    }
}
