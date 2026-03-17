public class WeedingEntry implements IEntry {
    private String method;
    private String chemical;
    public WeedingEntry(String method, String chemical) { this.method = method; this.chemical = chemical; }
    public WeedingEntry(WeedingEntry other) { this.method = other.method; this.chemical = other.chemical; }
    public String getMethod() { return method; }
    public String getChemical() { return chemical; }
    @Override public String getInfo() {
        return String.format("Прополка: метод %s, химикат %s", method, chemical);
    }
    @Override public WeedingEntry clone() { return new WeedingEntry(this); }
}
