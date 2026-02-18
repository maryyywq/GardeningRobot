public class LeggedMovement implements IMovementSystem {
    private double speed = 0.5;
    @Override public void moveTo(Location target) {
        System.out.println("Система передвижения на ножках: перемещение к " + target);
    }
    @Override public void stop() { System.out.println("Система передвижения на ножках: остановка"); }
    @Override public void setSpeed(double speed) { this.speed = speed; System.out.println("Система передвижения на ножках: скорость установлена " + speed); }
}
