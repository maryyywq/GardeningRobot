import java.util.*;

//Инструмент для внесения удобрений
public class FertilizingTool extends BaseTool {
    public FertilizingTool() { super("Удобрение", ToolType.FERTILIZING); } //Имя и тип
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Удобрение': внесение удобрений " + params);
    }
}
