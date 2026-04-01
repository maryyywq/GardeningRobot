import java.util.ArrayList;
import java.util.List;

public class MapSegment {
    private final SoilType soil;
    private final PlantType plant;
    private final double moisture;
    private final WeedLevel weeds;
    private final List<GroundObjectType> objects;
    // Конструктор с пакетным доступом, вызывается только из Builder
    MapSegment(SoilType soil, PlantType plant, double moisture,
               WeedLevel weeds, List<GroundObjectType> objects) {
        this.soil = soil;
        this.plant = plant;
        this.moisture = moisture;
        this.weeds = weeds;
        this.objects = new ArrayList<>(objects);
    }

    // Геттеры (при необходимости)
    public SoilType getSoil() { return soil; }
    public PlantType getPlant() { return plant; }
    public double getMoisture() { return moisture; }
    public WeedLevel getWeeds() { return weeds; }
    public List<GroundObjectType> getObjects() { return new ArrayList<>(objects); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Сегмент {почва=%s, растение=%s, влажность=%.1f, сорняки=%s",
                soil.getName(), plant.getName(), moisture, weeds));
        if (!objects.isEmpty()) {
            sb.append(", объекты: ").append(objects);
        }
        sb.append("}");
        return sb.toString();
    }
}


