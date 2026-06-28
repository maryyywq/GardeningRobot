package factories;

import core.ITool;
import tools.MedicalTool;

public class MedicalToolFabric implements ToolFabric {
    @Override
    public ITool create() {
        return new MedicalTool();
    }
}
