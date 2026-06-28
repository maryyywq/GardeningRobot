package factories;

import core.ITool;
import tools.MowingTool;

public class MowingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new MowingTool();
    }
}
