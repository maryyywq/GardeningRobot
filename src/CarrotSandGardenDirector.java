public class CarrotSandGardenDirector implements MapSegmentDirector {
    @Override
    public void construct(MapSegmentBuilder builder) {
        builder.setSoil(SoilType.SAND)
                .setPlant(PlantType.CARROT)
                .setMoisture(1.2)
                .setWeeds(WeedLevel.MEDIUM)
                .addObject(GroundObjectType.STONE)
                .addObject(GroundObjectType.STONE);
    }
}
