public class MowingEntry implements IEntry {
    private double height;
    private String shape;
    public MowingEntry(double height, String shape) { this.height = height; this.shape = shape; }
    public double getHeight() { return height; }
    public String getShape() { return shape; }
    @Override public String getInfo() {
        if (shape.isEmpty())
            return String.format("Стрижка: высота %.1f см", height);
        else
            return String.format("Стрижка: форма %s", shape);
    }
}
