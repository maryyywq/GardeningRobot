import java.util.Map;

//Абстрактный класс инструментов
abstract class BaseTool implements ITool{
    protected ToolStatus status = ToolStatus.READY; //Текущий статус инструмента
    protected String name; //Его название
    protected ToolType toolType; //Тип инструмента (для прополки, для полива и т.д.)
    protected double powerConsumption;
    public BaseTool(String name, ToolType toolType, double powerConsumption) { this.name = name; this.toolType = toolType; this.powerConsumption = powerConsumption; }

    @Override
    public void execute() {

    }

    @Override public ToolStatus getStatus() { return status; }
    @Override public ToolType getToolType() { return toolType; }
    @Override public String getName() { return name; }
    @Override public double getPowerConsumption() { return powerConsumption; }
}
