public class LeggedMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new LeggedMovement();
    }
}
