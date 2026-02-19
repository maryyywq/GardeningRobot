interface IMovementSystem {
    void moveTo(Location target);
    void stop();
    void setSpeed(double speed);
    MovementType getMovementType();
}
