public class MedicalEntry implements IEntry {
    private String medicine; //Название препарата
    private double dosage; //Дозировка в мл
    public MedicalEntry(String medicine, double dosage) { this.medicine = medicine; this.dosage = dosage; }
    public MedicalEntry(MedicalEntry other) { this.medicine = other.medicine; this.dosage = other.dosage; }
    public String getMedicine() { return medicine; }
    public double getDosage() { return dosage; }
    @Override public String getInfo() {
        return String.format("Лечение: препарат %s, дозировка %.1f мл", medicine, dosage);
    }
    @Override public MedicalEntry clone() { return new MedicalEntry(this); }
}
