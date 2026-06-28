package factories;

import components.movement.WheeledMovement;
import core.IMovementSystem;

public class WheeledMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new WheeledMovement();
    }
}
