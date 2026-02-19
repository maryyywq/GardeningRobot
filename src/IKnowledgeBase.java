//Интерфейс базы знаний
interface IKnowledgeBase<T extends IEntry> {
    void addEntry(String key, T value);
    void updateEntry(String key, T value);
    void removeEntry(String key);
    T getEntry(String key);
    boolean isToolCompatible(ITool tool);
}