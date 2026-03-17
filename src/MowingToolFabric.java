public class MowingToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new MowingTool();
    }
}
