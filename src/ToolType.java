enum ToolType {
    WATERING, FERTILIZING, WEEDING, MOWING, MEDICAL, PLANTING, HARVESTING;

    public String getDescription() {
        switch (this) {
            case WATERING: return "полив";
            case FERTILIZING: return "удобрение";
            case WEEDING: return "прополка";
            case MOWING: return "стрижка";
            case MEDICAL: return "лечение";
            case PLANTING: return "посадка";
            case HARVESTING: return "сбор урожая";
            default: return this.name();
        }
    }
}