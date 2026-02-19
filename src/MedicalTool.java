import java.util.*;

public class MedicalTool extends BaseTool {
    public MedicalTool() { super("Медикамент", ToolType.MEDICAL); }
    @Override public void execute() {
        System.out.println("Инструмент 'Медикамент': лечение растений ");
    }
}
