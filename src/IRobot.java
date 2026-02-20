//Интерфейс робота

interface IRobot {
    void startTask(); //Запустить выполнение задачи
    void stopTask();  //Остановить выполнение задачи
    RobotStatus getStatus(); //Получить текущий статус
    void setTool(ITool tool); //Установить инструмент
    void receiveCommand(String command); //Получить команду от контроллера
    boolean canUseTool(ITool tool); //Проверить, может ли робот использовать данный инструмент

    //Геттеры
    IMovementSystem getMovementSystem();
    INavigation getNavigation();
    IPowerSource getPowerSource();
    ICommunication getCommunication();
    IKnowledgeBase<?> getKnowledgeBase();
    ITool getCurrentTool();
}
