public class PlantingEntry implements IEntry {
    private int spacing; //Расстояние между растениями (в см)
    private int depth; //Глубина посадки (см)
    public PlantingEntry(int spacing, int depth) { this.spacing = spacing; this.depth = depth; }
    public PlantingEntry(PlantingEntry other) { this.spacing = other.spacing; this.depth = other.depth; }
    public int getSpacing() { return spacing; }
    public int getDepth() { return depth; }
    @Override public String getInfo() {
        return String.format("Посадка: расстояние %d см, глубина %d см", spacing, depth);
    }
    @Override public PlantingEntry clone() { return new PlantingEntry(this); }
}
