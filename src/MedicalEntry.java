public class MedicalEntry implements IEntry {
    private String medicine;
    private double dosage;
    public MedicalEntry(String medicine, double dosage) { this.medicine = medicine; this.dosage = dosage; }
    public String getMedicine() { return medicine; }
    public double getDosage() { return dosage; }
    @Override public String getInfo() {
        return String.format("Лечение: препарат %s, дозировка %.1f мл", medicine, dosage);
    }
}
