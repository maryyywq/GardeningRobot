//Интерфейс робота

interface IRobot extends IRobotObservable {
    RobotStatus getStatus(); //Получить текущий статус
    void setTool(ITool tool); //Установить инструмент
    void receiveCommand(String command); //Получить команду от контроллера
    boolean canUseTool(ITool tool); //Проверить, может ли робот использовать данный инструмент
    public ITool getCurrentTool();
    String getRobotId();
    IMovementSystem getMovementSystem();
    void setToolPool(ToolPool pool);
    void handleError();
    void resetError();
}
