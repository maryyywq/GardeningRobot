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

    // Конструктор копирования
    public BaseTool(BaseTool other) {
        this.name = other.name;
        this.toolType = other.toolType;
        this.powerConsumption = other.powerConsumption;
    }
    @Override public abstract ITool clone();
    @Override public ToolStatus getStatus() { return status; }
    @Override public ToolType getToolType() { return toolType; }
    @Override public String getName() { return name; }
    @Override public double getPowerConsumption() { return powerConsumption; }
    @Override
    public void reset() {
        this.status = ToolStatus.READY;
    }
    @Override
    public String toString() {
        return name + " (" + toolType.getDescription() + ")";
    }
}
