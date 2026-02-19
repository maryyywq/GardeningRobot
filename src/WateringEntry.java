public class WateringEntry implements IEntry {
    private double volume; //Объем (в литрах)
    private String mode; //Тип полива
    public WateringEntry(double volume, String mode) { this.volume = volume; this.mode = mode; }
    public double getVolume() { return volume; }
    public String getMode() { return mode; }
    @Override public String getInfo() {
        return String.format("Полив: объем %.1f л, режим %s", volume, mode);
    }
}
