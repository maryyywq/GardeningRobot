public class Location implements Prototype<Location>  {
    public final double x, y;
    public Location(double x, double y) { this.x = x; this.y = y; }
    @Override public String toString() { return String.format("(%.2f, %.2f)", x, y); }
    @Override public Location clone() { return new Location(this.x, this.y); }
}
