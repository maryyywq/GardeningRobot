public class HarvestingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new HarvestingTool();
    }
}
