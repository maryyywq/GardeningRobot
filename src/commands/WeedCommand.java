package commands;

import core.ITool;
import models.ToolType;
import robot.Robot;

public class WeedCommand extends TaskCommand {
    public WeedCommand() {
        super(ToolType.WEEDING, "Прополка");
    }

    @Override
    protected void executeTask(Robot robot, ITool tool) {
        System.out.println(robot.getRobotId() + ": удаление сорняков");
        tool.execute();
    }
    @Override
    public String toString() {
        return "Прополка сорняков";
    }
}