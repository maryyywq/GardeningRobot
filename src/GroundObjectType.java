public enum GroundObjectType {
    STONE("камень"),
    PUDDLE("лужица"),
    BRANCH("ветка");

    private final String name;
    GroundObjectType(String name) { this.name = name; }
    @Override
    public String toString() { return name; }
}
