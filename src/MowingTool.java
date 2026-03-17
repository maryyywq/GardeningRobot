import java.util.*;

public class MowingTool extends BaseTool {
    public MowingTool(MowingTool other) { super(other); }
    public MowingTool() { super("Косилка", ToolType.MOWING, 4.0); }
    @Override public void execute() {
        System.out.println("Инструмент 'Косилка': стрижка травы ");
    }
    @Override public ITool clone() { return new MowingTool(this); }
}
