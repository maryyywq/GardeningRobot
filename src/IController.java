import java.util.*;

//Интерфейс центрального контроллера
public interface IController {
    void assignTask(String robotId, Task task); //Назначить задачу конкретному роботу
    Map<String, RobotStatus> monitorRobots(); //Получить статусы всех зарегистрированных роботов
}
