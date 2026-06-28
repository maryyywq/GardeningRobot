package map;

import models.Location;
import models.PlantType;
import models.SoilType;
import models.WeedLevel;

import java.util.HashMap;
import java.util.Map;

public class MapSegmentFactory {
    private Map<Location, MapSegment> cache = new HashMap<>();

    public MapSegment getMapSegment(Location loc) {
        if (cache.containsKey(loc)) {
            System.out.println("Flyweight: возврат существующего сегмента для " + loc);
            return cache.get(loc);
        }

        // Создаём сегмент по умолчанию через билдер
        MapSegment defaultSegment = new StandartMapSegmentBuilder()
                .setSoil(SoilType.GRASS)
                .setPlant(PlantType.GRASS)
                .setMoisture(1.0)
                .setWeeds(WeedLevel.NONE)
                .build();

        cache.put(loc, defaultSegment);
        System.out.println("Flyweight: создан новый сегмент для " + loc + " со значениями по умолчанию");
        return defaultSegment;
    }

    // Дополнительный метод для регистрации заранее сконфигурированных сегментов
    public void registerSegment(Location loc, MapSegment segment) {
        cache.put(loc, segment);
        System.out.println("Flyweight: зарегистрирован кастомный сегмент для " + loc);
    }
}