public class MapSegment {
    private String soilType;      // тип почвы
    private String plant;          // культура
    private double watteringLevel;  // базовая влажность

    public MapSegment(String soilType, String crop, double baseMoisture) {
        this.soilType = soilType;
        this.plant = plant;
        this.watteringLevel = baseMoisture;
    }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public String getCrop() { return plant; }
    public void setCrop(String crop) { this.plant = plant; }

    public double getWatteringLevel() { return watteringLevel; }
    public void setWatteringLevel(double watteringLevel) { this.watteringLevel = watteringLevel; }

    @Override
    public String toString() {
        return String.format("Сегмент {почва='%s', культура='%s', влажность=%.1f}",
                soilType, plant, watteringLevel);
    }
}

