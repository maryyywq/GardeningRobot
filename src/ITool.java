import java.util.*;

interface ITool extends Prototype<ITool>{
    void execute(); //Выполнить операцию с заданными параметрами
    ToolStatus getStatus(); //Получить состояние инструмента (готов, занят, ошибка)
    ToolType getToolType(); //Получить тип инструмента
    String getName();  //Получить название инструмента
    double getPowerConsumption();
    void reset();
}
