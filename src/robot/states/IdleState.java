package robot.states;

import core.IRobotState;
import models.RobotStatus;
import robot.Robot;

public class IdleState implements IRobotState {
    private final Robot robot;

    public IdleState(Robot robot) { this.robot = robot; }

    @Override
    public void enter() { System.out.println(robot.getRobotId() + ": вошёл в состояние ожидания"); }
    @Override
    public void execute() {System.out.println(robot.getRobotId() + ": абсолютно ничего не делаю"); }
    @Override
    public void exit() { System.out.println(robot.getRobotId() + ": вышел из состояния ожидания"); }
    @Override
    public RobotStatus getStatus() { return RobotStatus.IDLE; }

    @Override
    public IRobotState clone(Robot newRobot) {
        return new IdleState(newRobot);
    }
}
