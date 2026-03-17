public class WheeledMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new WheeledMovement();
    }
}
