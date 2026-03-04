enum RobotStatus {
    IDLE, WORKING, MOVING, CHARGING, ERROR;

    public String getDescription() {
        switch (this) {
            case IDLE: return "ожидание";
            case WORKING: return "работа";
            case MOVING: return "движение";
            case CHARGING: return "зарядка";
            case ERROR: return "ошибка";
            default: return this.name();
        }
    }
}

