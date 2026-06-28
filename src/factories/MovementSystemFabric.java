package factories;

import core.IMovementSystem;

public interface MovementSystemFabric {
    public abstract IMovementSystem create();
}
