public class OldWateringAdapter implements ITool {
    private OldWateringSystem oldSystem;
    private ToolStatus status = ToolStatus.READY;

    public OldWateringAdapter(OldWateringSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public void execute() {
        status = ToolStatus.BUSY;
        oldSystem.turnOn();
        System.out.println("Старая поливалка: полив в течение некоторого времени...");
        oldSystem.turnOff();
        status = ToolStatus.READY;
    }

    @Override
    public ToolStatus getStatus() {
        return status;
    }

    @Override
    public ToolType getToolType() {
        return ToolType.WATERING;
    }

    @Override
    public String getName() {
        return "Старая поливалка";
    }
}
