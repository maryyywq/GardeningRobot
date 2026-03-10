import java.util.*;

//Инструмент для сбора урожая
public class HarvestingTool extends BaseTool {
    public HarvestingTool() { super("Плодосборник", ToolType.HARVESTING, 7.5); }
    @Override public void execute() {
        System.out.println("Инструмент 'Плодосборник': сбор урожая ");
    }
}
