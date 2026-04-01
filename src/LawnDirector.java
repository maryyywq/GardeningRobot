public class LawnDirector implements MapSegmentDirector {
    @Override
    public void construct(MapSegmentBuilder builder) {
        builder.setSoil(SoilType.GRASS)
                .setPlant(PlantType.GRASS)
                .setMoisture(1.0)
                .setWeeds(WeedLevel.NONE)
                .addObject(GroundObjectType.BRANCH);
    }
}
