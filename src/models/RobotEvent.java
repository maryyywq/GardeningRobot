package models;

public class RobotEvent {
    private final String robotId;
    private final EventType eventType;
    private final Object data;

    public RobotEvent(String robotId, EventType eventType, Object data) {
        this.robotId = robotId;
        this.eventType = eventType;
        this.data = data;
    }

    public String getRobotId() { return robotId; }
    public EventType getEventType() { return eventType; }
    public Object getData() { return data; }
}