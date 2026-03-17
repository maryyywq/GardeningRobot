public class GPSNavigationFabric implements NavigationFabric {
    @Override
    public INavigation create(Location initialLocation) {
        GPSNavigation nav = new GPSNavigation();
        nav.setCurrentLocation(initialLocation);
        return nav;
    }
}
