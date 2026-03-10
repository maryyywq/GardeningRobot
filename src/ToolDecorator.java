abstract class ToolDecorator implements ITool {
    protected ITool wrapped;

    public ToolDecorator(ITool wrapped) {
        this.wrapped = wrapped;
    }

    @Override public void execute() { wrapped.execute(); }
    @Override public ToolStatus getStatus() { return wrapped.getStatus(); }
    @Override public ToolType getToolType() { return wrapped.getToolType(); }
    @Override public String getName() { return wrapped.getName(); }

    @Override public double getPowerConsumption(){
        return wrapped.getPowerConsumption();
    }
}
