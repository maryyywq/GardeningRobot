public interface ToolPool {
    ITool acquireTool(ToolType toolType);
    void releaseTool(ITool tool);
    int availableCount(ToolType toolType);
    void addTool(ITool tool);
    boolean removeTool(ITool tool);
}
