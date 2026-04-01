public interface MapSegmentBuilder {
    MapSegmentBuilder setSoil(SoilType soil);
    MapSegmentBuilder setPlant(PlantType plant);
    MapSegmentBuilder setMoisture(double moisture);
    MapSegmentBuilder setWeeds(WeedLevel weeds);
    MapSegmentBuilder addObject(GroundObjectType obj);
    MapSegmentBuilder removeObject(GroundObjectType obj);
    MapSegment build();
}
