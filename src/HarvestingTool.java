import java.util.*;

public class HarvestingTool extends BaseTool {
    public HarvestingTool() { super("Плодосборник"); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Плодосборник': сбор урожая " + params);
    }
}
