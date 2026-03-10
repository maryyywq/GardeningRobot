import java.util.*;

public class MowingTool extends BaseTool {
    public MowingTool() { super("Косилка", ToolType.MOWING, 4.0); }
    @Override public void execute() {
        System.out.println("Инструмент 'Косилка': стрижка травы ");
    }
}
