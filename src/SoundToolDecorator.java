//Декоратор, добавляющий звуковой эффект
class SoundToolDecorator extends ToolDecorator {
    private String sound;

    public SoundToolDecorator(ITool wrapped, String sound) {
        super(wrapped);
        this.sound = sound;
    }

    @Override
    public void execute() {
        System.out.println("Звук: " + sound + " (инструмент " + wrapped.getName() + ")");
        super.execute();
    }
}