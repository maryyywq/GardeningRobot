package pool;

import core.ITool;
import models.ToolType;

public interface ToolPool {
    ITool acquireTool(ToolType toolType); //получить инструмент
    void releaseTool(ITool tool); //освободить
    int availableCount(ToolType toolType);
    void addTool(ITool tool);
    boolean removeTool(ITool tool);
}
