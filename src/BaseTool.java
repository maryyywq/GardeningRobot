abstract class BaseTool implements ITool{
    protected ToolStatus status = ToolStatus.READY;
    protected String name;
    public BaseTool(String name) { this.name = name; }
    @Override public ToolStatus getStatus() { return status; }
}
