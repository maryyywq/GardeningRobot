package robot.states;

import core.IRobotState;
import models.RobotStatus;
import robot.Robot;

public class WorkingState implements IRobotState {
    private final Robot robot;

    public WorkingState(Robot robot) { this.robot = robot; }

    @Override
    public void enter() { System.out.println(robot.getRobotId() + ": вошёл в состояние работы"); }

    @Override
    public void execute() {
         System.out.println(robot.getRobotId() + ": сейчас работает...");
    }

    @Override
    public void exit() { System.out.println(robot.getRobotId() + ": вышел из состояния работы"); }

    @Override
    public RobotStatus getStatus() { return RobotStatus.WORKING; }

    @Override
    public IRobotState clone(Robot newRobot) {
        return new WorkingState(newRobot);
    }

}
