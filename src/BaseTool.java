//Абстрактный класс инструментов
abstract class BaseTool implements ITool{
    protected ToolStatus status = ToolStatus.READY; //Текущий статус инструмента
    protected String name; //Его название
    protected ToolType toolType; //Тип инструмента (для прополки, для полива и т.д.)
    public BaseTool(String name, ToolType toolType) { this.name = name; this.toolType = toolType; }
    @Override public ToolStatus getStatus() { return status; }
    @Override public ToolType getToolType() { return toolType; }
    @Override public String getName() { return name; }
}
