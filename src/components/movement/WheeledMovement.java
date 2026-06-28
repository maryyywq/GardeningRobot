package components.movement;

import core.IMovementSystem;
import models.Location;
import models.MovementSystemType;
import models.MovementType;

public class WheeledMovement extends IMovementSystem {
    public WheeledMovement() {speed = 1.0;}
    public WheeledMovement(WheeledMovement other) { this.speed = other.speed; }
    @Override public void moveTo(Location target) {
        System.out.println("Система передвижения на колесах: движение к " + target);
    }
    @Override public void stop() { System.out.println("Система передвижения на колесах: остановка"); }
    @Override public void setSpeed(double speed) { this.speed = speed; System.out.println("Система передвижения на колесах: скорость установлена " + speed); }
    @Override public MovementType getMovementType() { return MovementType.GROUND; }

    @Override
    public String toString() {
        return "Система передвижения на колесиках (скорость: " + speed + ")";
    }
    @Override
    public MovementSystemType getSystemType() {
        return MovementSystemType.WHEELED;
    }
    @Override public IMovementSystem clone() { return new WheeledMovement(this); }
}
