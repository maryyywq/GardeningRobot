class OldWateringAdapter implements ITool {
    private OldWateringSystem oldSystem;
    private ToolStatus status = ToolStatus.READY;

    public OldWateringAdapter(OldWateringSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public void execute() {
        status = ToolStatus.BUSY;
        System.out.println("Запуск старой поливалки");
        oldSystem.turnOn();
        System.out.println("Ожидание завершения полива...");
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
        return "Адаптер старой поливалки";
    }

    //Дополнительный метод для дозаправки старой системы
    public void refillOldSystem() {
        oldSystem.refill();
    }
}