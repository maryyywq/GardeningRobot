//Движение робота по типу "вертолет"
public class HelicopterMovement extends IMovementSystem {
    HelicopterMovement() {speed = 2.0;}
    public HelicopterMovement(HelicopterMovement other) { this.speed = other.speed; }
    @Override public void moveTo(Location target) {
        System.out.println("Система передвижения с винтом сверху (как у вертолета): полёт к " + target);
    }
    @Override public void stop() { System.out.println("Система передвижения с винтом сверху (как у вертолета): зависание"); }
    @Override public void setSpeed(double speed) { this.speed = speed; System.out.println("Система передвижения с винтом сверху (как у вертолета): скорость установлена " + speed);}
    @Override public MovementType getMovementType() { return MovementType.AIR; }
    @Override
    public MovementSystemType getSystemType() {
        return MovementSystemType.HELICOPTER;
    }
    @Override public IMovementSystem clone() { return new HelicopterMovement(this); }
    @Override
    public String toString() {
        return "Система передвижения с винтом сверху (скорость: " + speed + ")";
    }
}
