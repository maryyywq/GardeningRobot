public class PlaneMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new PlaneMovement();
    }
}
