import java.util.*;

class MedicalKnowledgeBase implements IKnowledgeBase<MedicalEntry> {
    private Map<String, MedicalEntry> storage = new HashMap<>();
    public MedicalKnowledgeBase() {}

    public MedicalKnowledgeBase(MedicalKnowledgeBase other) {
        for (Map.Entry<String, MedicalEntry> entry : other.storage.entrySet()) {
            this.storage.put(entry.getKey(), entry.getValue().clone());
        }
    }
    @Override public void addEntry(String key, MedicalEntry value) { storage.put(key, value); }
    @Override public void updateEntry(String key, MedicalEntry value) { storage.put(key, value); }
    @Override public void removeEntry(String key) { storage.remove(key); }
    @Override public MedicalEntry getEntry(String key) { return storage.get(key); }
    @Override public boolean isToolCompatible(ITool tool) {
        return tool.getToolType() == ToolType.MEDICAL;
    }
    @Override public IKnowledgeBase<?> clone() {
        return new MedicalKnowledgeBase(this);
    }
    @Override
    public String toString() {
        return String.format("База знаний лечения (записей: %d)", storage.size());
    }
}