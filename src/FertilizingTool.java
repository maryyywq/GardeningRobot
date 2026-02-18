import java.util.*;

public class FertilizingTool extends BaseTool {
    public FertilizingTool() { super("Удобрение"); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Удобрение': внесение удобрений " + params);
    }
}
