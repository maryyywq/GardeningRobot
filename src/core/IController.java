package core;

import models.RobotStatus;

import java.util.*;

//Интерфейс центрального контроллера
public interface IController extends IRobotObserver {
    void assignCommand(String robotId, ICommand command); //Назначить задачу конкретному роботу
    Map<String, RobotStatus> monitorRobots(); //Получить статусы всех зарегистрированных роботов
}
