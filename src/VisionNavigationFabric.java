public class VisionNavigationFabric implements NavigationFabric {
    @Override
    public INavigation create(Location initialLocation) {
        VisionNavigation nav = new VisionNavigation(); // пустой конструктор
        nav.setCurrentLocation(initialLocation);
        return nav;
    }
}
