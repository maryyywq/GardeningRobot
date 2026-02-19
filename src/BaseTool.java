abstract class BaseTool implements ITool{
    protected ToolStatus status = ToolStatus.READY;
    protected String name;
    protected ToolType toolType;
    public BaseTool(String name, ToolType toolType) { this.name = name; this.toolType = toolType; }
    @Override public ToolStatus getStatus() { return status; }
    @Override public ToolType getToolType() { return toolType; }
}
