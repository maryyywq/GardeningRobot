package factories;

import core.ITool;
import tools.PlantingTool;

public class PlantingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new PlantingTool();
    }
}
