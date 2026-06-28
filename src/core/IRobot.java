package core;//Интерфейс робота

import map.MapSegment;
import models.RobotStatus;
import pool.ToolPool;

public interface IRobot extends IRobotObservable {
    RobotStatus getStatus(); //Получить текущий статус
    void setTool(ITool tool); //Установить инструмент
    boolean canUseTool(ITool tool); //Проверить, может ли робот использовать данный инструмент
    public ITool getCurrentTool();
    String getRobotId();
    IMovementSystem getMovementSystem();
    void setToolPool(ToolPool pool);
    void handleError();
    void resetError();

    MapSegment getCurrentSegment();
}
