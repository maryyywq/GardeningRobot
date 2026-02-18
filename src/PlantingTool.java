import java.util.*;

public class PlantingTool extends BaseTool {
    public PlantingTool() { super("Посадочная лопатка"); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Посадочная лопатка': посадка семян " + params);
    }
}
