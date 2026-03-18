//Декоратор, добавляющий подсветку с цветом и частотой мигания
class LightToolDecorator extends ToolDecorator {
    private String color;
    private int blinkRate; //частота мигания (раз в секунду)

    public LightToolDecorator(ITool wrapped, String color, int blinkRate) {
        super(wrapped);
        this.color = color;
        this.blinkRate = blinkRate;
    }

    public LightToolDecorator(LightToolDecorator other) {
        super(other.wrapped.clone()); // клонируем обёрнутый инструмент
        this.color = other.color;
        this.blinkRate = other.blinkRate;
    }

    @Override
    public void execute() {
        System.out.printf("Подсветка: %s, частота мигания %d раз/сек\n", color, blinkRate);
        super.execute();
        System.out.println("Подсветка выключена");
    }
    @Override
    public ITool clone() {
        return new LightToolDecorator(this);
    }

    @Override
    public void reset() {
        wrapped.reset();
    }

}