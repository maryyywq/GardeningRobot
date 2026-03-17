public class WeedingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new WeedingTool();
    }
}
