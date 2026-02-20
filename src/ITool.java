import java.util.*;

interface ITool {
    void execute(Map<String, Object> parameters); //Выполнить операцию с заданными параметрами
    ToolStatus getStatus(); //Получить состояние инструмента (готов, занят, ошибка)
    ToolType getToolType(); //Получить тип инструмента
    String getName();  //Получить название инструмента
}
