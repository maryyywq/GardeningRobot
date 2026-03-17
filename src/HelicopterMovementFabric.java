public class HelicopterMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new HelicopterMovement();
    }
}
