public class Location {
    public final double x, y;
    public Location(double x, double y) { this.x = x; this.y = y; }
    @Override public String toString() { return String.format("(%.2f, %.2f)", x, y); }
}
