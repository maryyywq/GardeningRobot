package factories;

import components.navigation.VisionNavigation;
import core.INavigation;
import models.Location;

public class VisionNavigationFabric implements NavigationFabric {
    @Override
    public INavigation create(Location initialLocation) {
        VisionNavigation nav = new VisionNavigation(); // пустой конструктор
        nav.setCurrentLocation(initialLocation);
        return nav;
    }
}
