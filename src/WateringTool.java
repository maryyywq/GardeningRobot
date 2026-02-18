import java.util.*;

public class WateringTool extends BaseTool {
    public WateringTool() { super("Лейка"); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Лейка': полив с параметрами " + params);
        status = ToolStatus.BUSY;
    }
}
