import java.util.*;

public class WeedingTool extends BaseTool {
    public WeedingTool(WeedingTool other) { super(other); }
    public WeedingTool() { super("Тяпка", ToolType.WEEDING, 7.0); }
    @Override public void execute() {
        System.out.println("Инструмент 'Тяпка': удаление сорняков ");
    }
    @Override public ITool clone() { return new WeedingTool(this); }
}
