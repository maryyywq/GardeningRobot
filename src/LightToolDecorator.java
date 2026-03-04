//Декоратор, добавляющий подсветку с цветом и частотой мигания
class LightToolDecorator extends ToolDecorator {
    private String color;
    private double blinkRate; //частота мигания (Гц)

    public LightToolDecorator(ITool wrapped, String color, double blinkRate) {
        super(wrapped);
        this.color = color;
        this.blinkRate = blinkRate;
    }

    @Override
    public void execute() {
        System.out.printf("Подсветка: %s, частота мигания %.1f Гц\n", color, blinkRate);
        super.execute();
        System.out.println("Подсветка выключена");
    }
}
