public interface IKnowledgeBase {
    void addEntry(String key, Object value);
    void updateEntry(String key, Object value);
    void removeEntry(String key);
    Object getEntry(String key);
    boolean isToolCompatible(ITool tool);
}
