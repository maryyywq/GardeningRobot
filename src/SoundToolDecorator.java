//Декоратор, добавляющий музыку
class SoundToolDecorator extends ToolDecorator {
    private String lyrics;

    public SoundToolDecorator(ITool wrapped, String lyrics) {
        super(wrapped);
        this.lyrics = lyrics;
    }
    public SoundToolDecorator(SoundToolDecorator other) {
        super(other.wrapped.clone()); // клонируем обёрнутый инструмент
        this.lyrics = other.lyrics;
    }

    @Override
    public void execute() {
        System.out.println("Музыка играет (доносятся слова): " + lyrics);
        super.execute();
        System.out.println("Музыка выключена");
    }
    @Override
    public ITool clone() {
        return new SoundToolDecorator(this);
    }

    @Override
    public void reset() {
        wrapped.reset();
    }
}
