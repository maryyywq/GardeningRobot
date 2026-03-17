import java.util.*;

//Инструмент для внесения удобрений
public class FertilizingTool extends BaseTool {
    public FertilizingTool(FertilizingTool other) { super(other); }
    public FertilizingTool() { super("Удобрение", ToolType.FERTILIZING, 9.1); } //Имя и тип
    @Override public void execute() {
        System.out.println("Инструмент 'Удобрение': внесение удобрений ");
    }
    @Override
    public String toString() {
        return name + " (" + toolType.getDescription() + ")";
    }
    @Override public ITool clone() { return new FertilizingTool(this); }
}
