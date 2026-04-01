public enum SoilType {
    CHERNOZEM("чернозём"),
    SAND("песок"),
    CLAY("глина"),
    GRASS("газонная смесь");

    private final String name;
    SoilType(String name) { this.name = name; }
    public String getName() { return name; }
}
