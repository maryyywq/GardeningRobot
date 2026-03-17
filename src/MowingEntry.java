public class MowingEntry implements IEntry {
    private double height; //Высота стрижки (в см)
    private String shape; //Форма (для кустов, например "шар", "конус")
    public MowingEntry(double height, String shape) { this.height = height; this.shape = shape; }
    public MowingEntry(MowingEntry other) { this.height = other.height; this.shape = other.shape; }
    public double getHeight() { return height; }
    public String getShape() { return shape; }
    @Override public String getInfo() {
        if (shape.isEmpty())
            return String.format("Стрижка: высота %.1f см", height);
        else
            return String.format("Стрижка: форма %s", shape);
    }
    @Override public MowingEntry clone() { return new MowingEntry(this); }
}
