package factories;

import components.movement.HelicopterMovement;
import core.IMovementSystem;

public class HelicopterMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new HelicopterMovement();
    }
}
