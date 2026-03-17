import java.util.*;

class MowingKnowledgeBase implements IKnowledgeBase<MowingEntry> {
    private Map<String, MowingEntry> storage = new HashMap<>();
    @Override public void addEntry(String key, MowingEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, MowingEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public MowingEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.MOWING;
    }
    public MowingKnowledgeBase() {}

    public MowingKnowledgeBase(MowingKnowledgeBase other) {
        for (Map.Entry<String, MowingEntry> entry : other.storage.entrySet()) {
            this.storage.put(entry.getKey(), entry.getValue().clone());
        }
    }
    @Override public IKnowledgeBase<?> clone() {
        return new MowingKnowledgeBase(this);
    }
}

