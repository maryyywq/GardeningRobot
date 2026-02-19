import java.util.*;

//База знаний робота, который собирает урожай
class HarvestingKnowledgeBase implements IKnowledgeBase<HarvestingEntry> {
    private Map<String, HarvestingEntry> storage = new HashMap<>();
    @Override public void addEntry(String key, HarvestingEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, HarvestingEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public HarvestingEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.HARVESTING;
    }
}