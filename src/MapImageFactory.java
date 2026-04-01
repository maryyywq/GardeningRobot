import java.util.HashMap;
import java.util.Map;

public class MapImageFactory {
    private static Map<MapSegment, MapSegmentImage> cache = new HashMap<>();

    public static MapSegmentImage getImage(MapSegment segment) {
        if (cache.containsKey(segment)) {
            System.out.println("Фабрика изображений: Беру картинку из кэша... ");
            return cache.get(segment);
        }

        // Создаём новую картинку
        MapSegmentImage image;

        // Если растение – газон (значение по умолчанию), считаем грядку пустой
        if (segment.getPlant() == PlantType.GRASS) {
            image = new BrownImage();
        } else {
            image = new GreenImage();
        }
        System.out.println("Фабрика изображений: Создаю новую картинку...");
        cache.put(segment, image);
        return image;
    }
}