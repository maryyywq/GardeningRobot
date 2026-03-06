//Декоратор, добавляющий музыку
class SoundToolDecorator extends ToolDecorator {
    private String lyrics;

    public SoundToolDecorator(ITool wrapped, String lyrics) {
        super(wrapped);
        this.lyrics = lyrics;
    }

    @Override
    public void execute() {
        System.out.println("Музыка играет (доносятся слова): " + lyrics);
        super.execute();
        System.out.println("Музыка выключена");
    }
}
