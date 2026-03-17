//Запись базы знаний для сбора урожая
public class HarvestingEntry implements IEntry {
    private String ripenessIndicator; //Признак зрелости
    private String method; //Метод сбора
    public HarvestingEntry(String ripenessIndicator, String method) { this.ripenessIndicator = ripenessIndicator; this.method = method; }
    public HarvestingEntry(HarvestingEntry other) { this.ripenessIndicator = other.ripenessIndicator; this.method = other.method; }
    public String getRipenessIndicator() { return ripenessIndicator; }
    public String getMethod() { return method; }
    @Override public String getInfo() {
        return String.format("Сбор урожая: спелость %s, метод %s", ripenessIndicator, method);
    }
    @Override public HarvestingEntry clone() { return new HarvestingEntry(this); }
}
