import java.util.*;

public class MowingKnowledgeBase implements IKnowledgeBase {
    private Map<String, Object> storage = new HashMap<>();
    @Override public void addEntry(String key, Object value) { storage.put(key, value); }
    @Override public void updateEntry(String key, Object value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public Object getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool instanceof MowingTool;
    }
}
