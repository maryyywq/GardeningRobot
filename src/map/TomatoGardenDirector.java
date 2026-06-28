package map;

import models.GroundObjectType;
import models.PlantType;
import models.SoilType;
import models.WeedLevel;

public class TomatoGardenDirector implements MapSegmentDirector {
    @Override
    public void construct(MapSegmentBuilder builder) {
        builder.setSoil(SoilType.CHERNOZEM)
                .setPlant(PlantType.TOMATO)
                .setMoisture(2.5)
                .setWeeds(WeedLevel.LOW)
                .addObject(GroundObjectType.STONE)
                .addObject(GroundObjectType.STONE)   // два камня
                .addObject(GroundObjectType.PUDDLE);
    }
}
