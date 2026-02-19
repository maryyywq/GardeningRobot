import java.util.*;

//База знаний робота-удобрителя
class FertilizingKnowledgeBase implements IKnowledgeBase<FertilizingEntry> {
    private Map<String, FertilizingEntry> storage = new HashMap<>();
    @Override public void addEntry(String key, FertilizingEntry value) { storage.put(key, value); } //Добавление записи
    @Override public void updateEntry(String key, FertilizingEntry value) { storage.put(key, value); } //Обновление записи
    @Override public void removeEntry(String key) { storage.remove(key); } //Удаление записи
    @Override public FertilizingEntry getEntry(String key) { return storage.get(key); } //Получение записи
    @Override public boolean isToolCompatible(ITool tool) {
        //Проверяет, что инструмент предназначен для внесения удобрений
        return tool.getToolType() == ToolType.FERTILIZING;
    }
}