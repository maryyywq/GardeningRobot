import java.util.*;

class PlantingKnowledgeBase implements IKnowledgeBase<PlantingEntry> {
    private Map<String, PlantingEntry> storage = new HashMap<>();
    public PlantingKnowledgeBase() {}

    public PlantingKnowledgeBase(PlantingKnowledgeBase other) {
        for (Map.Entry<String, PlantingEntry> entry : other.storage.entrySet()) {
            this.storage.put(entry.getKey(), entry.getValue().clone());
        }
    }
    @Override public void addEntry(String key, PlantingEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, PlantingEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public PlantingEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.PLANTING;
    }
    @Override public IKnowledgeBase<?> clone() {
        return new PlantingKnowledgeBase(this);
    }
    @Override
    public String toString() {
        return String.format("База знаний посадки (записей: %d)", storage.size());
    }
}
