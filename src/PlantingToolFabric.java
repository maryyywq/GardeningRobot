public class PlantingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new PlantingTool();
    }
}
