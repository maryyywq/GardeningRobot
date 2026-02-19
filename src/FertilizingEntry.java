public class FertilizingEntry implements IEntry {
    private String fertilizerType;
    private double dosage;
    public FertilizingEntry(String fertilizerType, double dosage) { this.fertilizerType = fertilizerType; this.dosage = dosage; }
    public String getFertilizerType() { return fertilizerType; }
    public double getDosage() { return dosage; }
    @Override public String getInfo() {
        return String.format("Удобрение: %s, дозировка %.1f г", fertilizerType, dosage);
    }
}
