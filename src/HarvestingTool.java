import java.util.*;

//Инструмент для сбора урожая
public class HarvestingTool extends BaseTool {
    public HarvestingTool() { super("Плодосборник", ToolType.HARVESTING); }
    @Override public void execute() {
        System.out.println("Инструмент 'Плодосборник': сбор урожая ");
    }
}
