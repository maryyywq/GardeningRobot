import java.util.*;

public class MedicalTool extends BaseTool {
    public MedicalTool() { super("Медикамент", ToolType.MEDICAL); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Медикамент': лечение растений " + params);
    }
}
