import java.util.*;

public class WateringTool extends BaseTool {
    public WateringTool() { super("Лейка", ToolType.WATERING); }
    @Override public void execute() {
        System.out.println("Инструмент 'Лейка': полив с параметрами ");
        status = ToolStatus.BUSY;
    }
}
