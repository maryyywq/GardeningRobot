package factories;

import core.ITool;
import tools.FertilizingTool;

public class FertilizingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new FertilizingTool();
    }
}
