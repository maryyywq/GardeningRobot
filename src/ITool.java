import java.util.*;

interface ITool {
    void execute(Map<String, Object> parameters);
    ToolStatus getStatus();
    ToolType getToolType();
}
