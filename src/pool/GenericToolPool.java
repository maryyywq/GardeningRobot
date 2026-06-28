package pool;

import core.ITool;
import models.ToolType;

import java.util.*;

public class GenericToolPool implements ToolPool {
    private final Map<ToolType, Queue<ITool>> available = new EnumMap<>(ToolType.class);

    public GenericToolPool(Map<ToolType, List<ITool>> initialTools) {
        for (Map.Entry<ToolType, List<ITool>> entry : initialTools.entrySet()) { //проходим по всем записям
            Queue<ITool> queue = new ArrayDeque<>(entry.getValue()); //создаем очередь из инструментов
            available.put(entry.getKey(), queue);
        }
    }

    @Override
    public synchronized ITool acquireTool(ToolType toolType) {
        Queue<ITool> queue = available.get(toolType); //получаем очередь из доступных инструментов
        if (queue == null || queue.isEmpty()) {
            return null; //нет свободных инструментов данного типа
        }
        return queue.poll(); //извлекаем
    }

    @Override
    public synchronized void releaseTool(ITool tool) {
        if (tool == null) return;
        ToolType type = tool.getToolType();
        Queue<ITool> queue = available.computeIfAbsent(type, k -> new ArrayDeque<>()); //если очередь с таким типом есть, то он ее вернет. иначе - создаст новую
        //Сбрасываем состояние инструмента перед возвратом
        tool.reset();
        queue.offer(tool);
    }

    public GenericToolPool() {
    }

    @Override
    public synchronized void addTool(ITool tool) {
        if (tool == null) return;
        tool.reset();
        ToolType type = tool.getToolType();
        available.computeIfAbsent(type, k -> new ArrayDeque<>()).offer(tool);
    }

    @Override
    public synchronized boolean removeTool(ITool tool) {
        if (tool == null) return false;
        ToolType type = tool.getToolType();
        Queue<ITool> queue = available.get(type); //получаем очередь
        if (queue != null) {
            //Удаляем конкретный инструмент (сравнение по ссылке)
            return queue.remove(tool);
        }
        return false;
    }

    public int availableCount(ToolType toolType) {
        Queue<ITool> queue = available.get(toolType);
        return queue != null ? queue.size() : 0;
    }
}
