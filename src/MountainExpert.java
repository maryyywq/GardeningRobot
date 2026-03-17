class MountainExpert extends RobotExpert {
    @Override
    public String getDescription() {
        return "Проверка пригодности для горной местности";
    }

    @Override
    public String check(IRobot robot) {
        IMovementSystem ms = robot.getMovementSystem();
        ITool tool = robot.getCurrentTool();

        MovementSystemType msType = ms.getSystemType();
        if (msType != MovementSystemType.WHEELED) {
            return "Не подходит: требуется колеса, а у робота " + msType;
        }

        if (tool == null) return "Инструмент не установлен";

        if (tool.getToolType() == ToolType.WATERING) {
            return "Не подходит: водный инструмент не рекомендуется в горах (установлен " + tool.getName() + ")";
        }

        return "Робот готов к работе в горной местности";
    }
}