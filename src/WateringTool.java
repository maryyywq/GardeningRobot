import java.util.*;

public class WateringTool extends BaseTool {
    public WateringTool(WateringTool other) { super(other); }
    public WateringTool() { super("Лейка", ToolType.WATERING, 5.0); }
    @Override public void execute() {
        System.out.println("Инструмент 'Лейка': полив с параметрами ");
        status = ToolStatus.BUSY;
    }
    @Override public ITool clone() { return new WateringTool(this); }
}
