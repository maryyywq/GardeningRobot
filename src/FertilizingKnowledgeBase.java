import java.util.*;

//База знаний робота-удобрителя
class FertilizingKnowledgeBase implements IKnowledgeBase<FertilizingEntry> {
    private Map<String, FertilizingEntry> storage = new HashMap<>();

    public FertilizingKnowledgeBase() {}

    // Конструктор копирования
    public FertilizingKnowledgeBase(FertilizingKnowledgeBase other) {
        for (Map.Entry<String, FertilizingEntry> entry : other.storage.entrySet()) {
            this.storage.put(entry.getKey(), entry.getValue().clone());
        }
    }
    @Override public void addEntry(String key, FertilizingEntry value) { storage.put(key, value); } //Добавление записи
    @Override public void updateEntry(String key, FertilizingEntry value) { storage.put(key, value); } //Обновление записи
    @Override public void removeEntry(String key) { storage.remove(key); } //Удаление записи
    @Override public FertilizingEntry getEntry(String key) { return storage.get(key); } //Получение записи
    @Override public boolean isToolCompatible(ITool tool) {
        //Проверяет, что инструмент предназначен для внесения удобрений
        return tool.getToolType() == ToolType.FERTILIZING;
    }
    @Override public IKnowledgeBase<?> clone() {
        return new FertilizingKnowledgeBase(this);
    }
    @Override
    public String toString() {
        return String.format("База знаний удобрения (записей: %d)", storage.size());
    }
}