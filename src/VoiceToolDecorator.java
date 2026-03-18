class VoiceToolDecorator extends ToolDecorator {
    private String ownerName;

    public VoiceToolDecorator(ITool wrapped, String ownerName) {
        super(wrapped);
        this.ownerName = ownerName;
    }

    public VoiceToolDecorator(VoiceToolDecorator other) {
        super(other.wrapped.clone()); // клонируем обёрнутый инструмент
        this.ownerName = other.ownerName;
    }

    @Override
    public void execute() {
        System.out.println("Голосовой помощник включен! Дорогой(ая), " + ownerName + ", я начинаю работу!");
        super.execute();
        System.out.println("Голосовой помощник выключается. " + ownerName + ", задача выполнена!");
    }
    @Override
    public ITool clone() {
        return new VoiceToolDecorator(this);
    }

    @Override
    public void reset() {
        wrapped.reset();
    }
}