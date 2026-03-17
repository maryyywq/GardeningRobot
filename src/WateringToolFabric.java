public class WateringToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new WateringTool();
    }
}
