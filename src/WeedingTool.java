import java.util.*;

public class WeedingTool extends BaseTool {
    public WeedingTool() { super("Тяпка"); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Тяпка': удаление сорняков " + params);
    }
}
