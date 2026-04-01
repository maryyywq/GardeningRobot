import java.util.ArrayList;
import java.util.List;

public class StandartMapSegmentBuilder implements MapSegmentBuilder {
    private SoilType soil = SoilType.GRASS;
    private PlantType plant = PlantType.GRASS;
    private double moisture = 1.0;
    private WeedLevel weeds = WeedLevel.NONE;
    private List<GroundObjectType> objects = new ArrayList<>();

    @Override
    public StandartMapSegmentBuilder setSoil(SoilType soil) {
        this.soil = soil;
        return this;
    }

    @Override
    public StandartMapSegmentBuilder setPlant(PlantType plant) {
        this.plant = plant;
        return this;
    }

    @Override
    public StandartMapSegmentBuilder setMoisture(double moisture) {
        this.moisture = moisture;
        return this;
    }

    @Override
    public StandartMapSegmentBuilder setWeeds(WeedLevel weeds) {
        this.weeds = weeds;
        return this;
    }

    @Override
    public StandartMapSegmentBuilder addObject(GroundObjectType obj) {
        if (obj != null) objects.add(obj);
        return this;
    }

    @Override
    public StandartMapSegmentBuilder removeObject(GroundObjectType obj) {
        objects.remove(obj);
        return this;
    }

    @Override
    public MapSegment build() {
        return new MapSegment(soil, plant, moisture, weeds, objects);
    }
}
