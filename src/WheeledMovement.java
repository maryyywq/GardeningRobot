public class WheeledMovement implements IMovementSystem {
    private double speed = 1.0;
    @Override public void moveTo(Location target) {
        System.out.println("Система передвижения на колесах: движение к " + target);
    }
    @Override public void stop() { System.out.println("Система передвижения на колесах: остановка"); }
    @Override public void setSpeed(double speed) { this.speed = speed; System.out.println("Система передвижения на колесах: скорость установлена " + speed); }
}
