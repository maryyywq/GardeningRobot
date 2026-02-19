public class HelicopterMovement implements IMovementSystem {
    private double speed = 2.0;
    @Override public void moveTo(Location target) {
        System.out.println("Система передвижения с винтом сверху (как у вертолета): полёт к " + target);
    }
    @Override public void stop() { System.out.println("Система передвижения с винтом сверху (как у вертолета): зависание"); }
    @Override public void setSpeed(double speed) { this.speed = speed; System.out.println("Система передвижения с винтом сверху (как у вертолета): скорость установлена " + speed);}
    @Override public MovementType getMovementType() { return MovementType.AIR; }
}
