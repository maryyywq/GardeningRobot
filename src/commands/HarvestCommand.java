package commands;

import core.ITool;
import models.ToolType;
import robot.Robot;

public class HarvestCommand extends TaskCommand {
    public HarvestCommand() {
        super(ToolType.HARVESTING, "Сбор урожая");
    }

    @Override
    protected void executeTask(Robot robot, ITool tool) {
        System.out.println(robot.getRobotId() + ": сбор урожая");
        tool.execute();
    }

    @Override
    public String toString() {
        return "Сбор урожая";
    }
}