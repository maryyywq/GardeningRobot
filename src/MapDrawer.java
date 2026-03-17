import java.util.ArrayList;
import java.util.List;

public class MapDrawer {
    private List<MapSegment> map;

    public MapDrawer() {
        map = new ArrayList<>();
    }

    public MapDrawer(List<MapSegment> map) {
        this.map = map;
    }

    public void draw() {
        System.out.println("Отрисовка участков карты:");
        for (var segment : map) {
            MapSegmentImage image = MapImageFactory.getImage(segment);
            image.draw();
        }
    }
}
