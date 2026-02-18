public class PlainMovement implements IMovementSystem {
    private double speed = 3.0;
    @Override public void moveTo(Location target) {
        System.out.println("Система передвижения с использованием винта передней части (как у самолета): планирование к " + target);
    }
    @Override public void stop() { System.out.println("Система передвижения с использованием винта передней части (как у самолета): торможение"); }
    @Override public void setSpeed(double speed) { this.speed = speed; System.out.println("Система передвижения с использованием винта передней части (как у самолета): скорость установлена " + speed); }
}
