package factories;

import components.movement.PlaneMovement;
import core.IMovementSystem;

public class PlaneMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new PlaneMovement();
    }
}
