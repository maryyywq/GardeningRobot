//Запись базы знаний для удобрения растений
public class FertilizingEntry implements IEntry {
    private String fertilizerType; //Название удобрения
    private double dosage; //Доза удобрения (в граммах)
    public FertilizingEntry(String fertilizerType, double dosage) { this.fertilizerType = fertilizerType; this.dosage = dosage; }
    public FertilizingEntry(FertilizingEntry other) { this.fertilizerType = other.fertilizerType; this.dosage = other.dosage; }
    public String getFertilizerType() { return fertilizerType; }
    public double getDosage() { return dosage; }
    @Override public String getInfo() {
        return String.format("Удобрение: %s, дозировка %.1f г", fertilizerType, dosage);
    }
    @Override public FertilizingEntry clone() { return new FertilizingEntry(this); }
}
