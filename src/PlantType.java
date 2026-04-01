public enum PlantType {
    TOMATO("томаты"),
    CUCUMBER("огурцы"),
    CARROT("морковь"),
    GRASS("газон");

    private final String name;
    PlantType(String name) { this.name = name; }
    public String getName() { return name; }
}
