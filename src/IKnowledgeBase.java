//Интерфейс базы знаний
interface IKnowledgeBase<T extends IEntry> {
    void addEntry(String key, T value);  //Добавить новую запись по ключу
    void updateEntry(String key, T value); //Обновить существующую запись
    void removeEntry(String key); //Удалить запись по ключу
    T getEntry(String key); //Получить запись по ключу
    boolean isToolCompatible(ITool tool); //Проверить, совместим ли данный инструмент с этой базой знаний
}