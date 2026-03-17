public interface NavigationFabric {
    public abstract INavigation create(Location initialLocation);

    default public INavigation create() {
        return create(new Location(0,0));
    }
}
