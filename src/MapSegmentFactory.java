import java.util.HashMap;
import java.util.Map;

class MapSegmentFactory {
    // Константы для значений по умолчанию
    private static final String DEFAULT_SOIL_TYPE = "обычная";
    private static final String DEFAULT_CROP = "газон";
    private static final double DEFAULT_MOISTURE = 1.0;

    private Map<Location, MapSegment> cache = new HashMap<>();

    public MapSegment getMapSegment(Location loc) {
        if (cache.containsKey(loc)) {
            System.out.println("Flyweight: возврат существующего сегмента для " + loc);
            return cache.get(loc);
        }

        // Создаём новый сегмент с константными значениями по умолчанию
        MapSegment segment = new MapSegment(DEFAULT_SOIL_TYPE, DEFAULT_CROP, DEFAULT_MOISTURE);
        cache.put(loc, segment);
        System.out.println("Flyweight: создан новый сегмент для " + loc + " со значениями по умолчанию");
        return segment;
    }
}