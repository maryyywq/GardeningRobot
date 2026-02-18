import java.util.*;

public class MowingTool extends BaseTool {
    public MowingTool() { super("Косилка"); }
    @Override public void execute(Map<String, Object> params) {
        System.out.println("Инструмент 'Косилка': стрижка травы " + params);
    }
}
