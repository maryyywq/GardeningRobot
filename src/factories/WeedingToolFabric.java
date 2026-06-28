package factories;

import core.ITool;
import tools.WeedingTool;

public class WeedingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new WeedingTool();
    }
}
