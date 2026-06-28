package commands;

import core.ITool;
import models.ToolType;
import robot.Robot;

public class WaterCommand extends TaskCommand {
    private final double volume;

    public WaterCommand(double volume) {
        super(ToolType.WATERING, "Полив");
        this.volume = volume;
    }

    @Override
    protected void executeTask(Robot robot, ITool tool) {
        if (volume <= 0) {
            System.out.println(robot.getRobotId() + ": ошибка - объём полива должен быть положительным");
            return;
        }
        System.out.println(robot.getRobotId() + ": полив с объёмом " + volume + " л");
        tool.execute();
    }

    @Override
    public String toString() {
        return "Полив объёмом " + volume + " л";
    }
}