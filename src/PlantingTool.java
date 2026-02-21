import java.util.*;

public class PlantingTool extends BaseTool {
    public PlantingTool() { super("Посадочная лопатка", ToolType.PLANTING); }
    @Override public void execute() {
        System.out.println("Инструмент 'Посадочная лопатка': посадка семян ");
    }
}
