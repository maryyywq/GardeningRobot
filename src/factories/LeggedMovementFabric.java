package factories;

import components.movement.LeggedMovement;
import core.IMovementSystem;

public class LeggedMovementFabric implements MovementSystemFabric {
    @Override
    public IMovementSystem create() {
        return new LeggedMovement();
    }
}
