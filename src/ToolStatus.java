enum ToolStatus {
    READY, BUSY, ERROR;

    public String getDescription() {
        switch (this) {
            case READY: return "готов";
            case BUSY: return "занят";
            case ERROR: return "ошибка";
            default: return this.name();
        }
    }
}