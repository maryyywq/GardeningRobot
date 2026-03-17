import java.util.*;
//База знаний для полива
class WateringKnowledgeBase implements IKnowledgeBase<WateringEntry> {

    //Хранилище данных: ключ - название растения, значение - запись WateringEntry
    private Map<String, WateringEntry> storage = new HashMap<>();

    public WateringKnowledgeBase() {}
    public WateringKnowledgeBase(WateringKnowledgeBase other) {
        for (Map.Entry<String, WateringEntry> entry : other.storage.entrySet()) {
            this.storage.put(entry.getKey(), entry.getValue().clone());
        }
    }
    @Override public void addEntry(String key, WateringEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, WateringEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public WateringEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.WATERING;
    }
    @Override
    public String toString() {
        return String.format("База знаний полива (записей: %d)", storage.size());
    }
    @Override public IKnowledgeBase<?> clone() { return new WateringKnowledgeBase(this); }
}