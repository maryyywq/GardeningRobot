package controller;

import core.ICommand;
import core.IMapSegmentVisitor;
import models.RobotStatus;
import robot.Robot;

public class RobotTaskHandler extends TaskHandler {
    private final Robot robot;

    public RobotTaskHandler(Robot robot) {
        this.robot = robot;
    }

    @Override
    protected boolean canHandle(ICommand command) {
        return command.canBeHandledBy(robot);
    }

    @Override
    protected boolean doHandle(ICommand command) {
        System.out.println("[Цепь роботов] Робот " + robot.getRobotId() + " выполняет команду");
        command.execute(robot);
        return robot.getStatus() != RobotStatus.ERROR;
    }

    @Override
    public boolean handle(ICommand command) {
        if (canHandle(command)) {
            return doHandle(command);
        } else {
            System.out.println("[Цепь роботов] Робот " + robot.getRobotId() + " не может выполнить команду");
            if (next != null) {
                return next.handle(command);
            } else {
                System.out.println("[Цепь роботов] Ни один робот не смог выполнить команду");
                return false;
            }
        }
    }

    @Override
    public boolean handle(ICommand command, IMapSegmentVisitor visitor) {
        boolean isDone = handle(command);
        visitor.visit(robot.getCurrentSegment(), robot, command);
        return isDone;
    }
}