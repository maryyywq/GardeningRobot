import java.util.*;

class WeedingKnowledgeBase implements IKnowledgeBase<WeedingEntry> {
    private Map<String, WeedingEntry> storage = new HashMap<>();
    @Override public void addEntry(String key, WeedingEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, WeedingEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public WeedingEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.WEEDING;
    }
}