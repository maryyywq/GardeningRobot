import java.util.*;

class FertilizingKnowledgeBase implements IKnowledgeBase<FertilizingEntry> {
    private Map<String, FertilizingEntry> storage = new HashMap<>();
    @Override public void addEntry(String key, FertilizingEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, FertilizingEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public FertilizingEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.FERTILIZING;
    }
}