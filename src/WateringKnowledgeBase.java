import java.util.*;
//База знаний для полива
class WateringKnowledgeBase implements IKnowledgeBase<WateringEntry> {
    //Хранилище данных: ключ - название растения, значение - запись WateringEntry
    private Map<String, WateringEntry> storage = new HashMap<>();
    @Override public void addEntry(String key, WateringEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, WateringEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public WateringEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.WATERING;
    }
}