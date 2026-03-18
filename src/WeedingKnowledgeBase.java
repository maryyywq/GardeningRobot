import java.util.*;

class WeedingKnowledgeBase implements IKnowledgeBase<WeedingEntry> {
    private Map<String, WeedingEntry> storage = new HashMap<>();
    public WeedingKnowledgeBase() {}

    public WeedingKnowledgeBase(WeedingKnowledgeBase other) {
        for (Map.Entry<String, WeedingEntry> entry : other.storage.entrySet()) {
            this.storage.put(entry.getKey(), entry.getValue().clone());
        }
    }
    @Override public void addEntry(String key, WeedingEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, WeedingEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public WeedingEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.WEEDING;
    }
    @Override
    public String toString() {
        return String.format("База знаний прополки (записей: %d)", storage.size());
    }
    @Override public IKnowledgeBase<?> clone() {
        return new WeedingKnowledgeBase(this);
    }

}