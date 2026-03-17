public class FertilizingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new FertilizingTool();
    }
}
