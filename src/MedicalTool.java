import java.util.*;

public class MedicalTool extends BaseTool {
    public MedicalTool() { super("Медикамент", ToolType.MEDICAL, 10.0); }
    @Override public void execute() {
        System.out.println("Инструмент 'Медикамент': лечение растений ");
    }
}
