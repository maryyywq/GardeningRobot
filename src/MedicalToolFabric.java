public class MedicalToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new MedicalTool();
    }
}
