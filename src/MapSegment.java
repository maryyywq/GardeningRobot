import java.util.ArrayList;
import java.util.List;

public class MapSegment {
    private SoilType soil;
    private PlantType plant;
    private double moisture;
    private WeedLevel weeds;
    private List<GroundObjectType> objects;

    MapSegment(SoilType soil, PlantType plant, double moisture,
               WeedLevel weeds, List<GroundObjectType> objects) {
        this.soil = soil;
        this.plant = plant;
        this.moisture = moisture;
        this.weeds = weeds;
        this.objects = new ArrayList<>(objects);
    }

    public void accept(IMapSegmentVisitor visitor, IRobot robot, ICommand command) {
        visitor.visit(this, robot, command);
    }

    public void setSoil(SoilType soil) { this.soil = soil; }
    public void setPlant(PlantType plant) { this.plant = plant; }
    public void setMoisture(double moisture) { this.moisture = moisture; }
    public void setWeeds(WeedLevel weeds) { this.weeds = weeds; }
    public void setObjects(List<GroundObjectType> objects) { this.objects = objects; }

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


