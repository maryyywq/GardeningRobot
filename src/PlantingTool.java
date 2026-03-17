import java.util.*;

public class PlantingTool extends BaseTool {
    public PlantingTool(PlantingTool other) { super(other); }
    public PlantingTool() { super("Посадочная лопатка", ToolType.PLANTING, 8.0); }
    @Override public void execute() {
        System.out.println("Инструмент 'Посадочная лопатка': посадка семян ");
    }
    @Override public ITool clone() { return new PlantingTool(this); }
}
