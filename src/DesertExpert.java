class DesertExpert extends RobotExpert {
    @Override
    public String getDescription() {
        return "Проверка пригодности для пустыни";
    }

    @Override
    public String check(IRobot robot) {
        IMovementSystem ms = robot.getMovementSystem();
        ITool tool = robot.getCurrentTool();

        MovementSystemType msType = ms.getSystemType();
        if (msType != MovementSystemType.LEGGED) {
            return "Не подходит: требуются ножки, а у робота " + msType;
        }

        if (tool == null) return "Инструмент не установлен";

        if (tool.getToolType() != ToolType.WATERING) {
            return "Не подходит: требуется инструмент полива, а у робота " + tool.getToolType();
        }

        return "Робот готов к работе в пустыне";
    }
}