import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Robot implements IRobot, Iterable<Object> , Prototype<Robot> {
    private List<IRobotObserver> observers = new ArrayList<>();
    protected String id; //Уникальный идентификатор робота
    private IRobotState currentState;
    protected IMovementSystem movementSystem; //Система передвижения
    protected INavigation navigation; //Навигационная система
    protected PowerManager powerManager;
    protected ICommunication communication; //Система связи
    protected IKnowledgeBase<?> knowledgeBase; //База знаний
    protected ITool currentTool; //Текущий установленный инструмент
    protected Location location; //Текущее местоположение
    private MapSegment currentSegment;
    private MapSegmentFactory segmentFactory;
    private ToolPool toolPool;
    private Location destination;

    public Robot() {
    }

    public Robot(String id, IMovementSystem ms, INavigation nav, PowerManager pm,
                 ICommunication comm, IKnowledgeBase<?> kb, Location startLoc, MapSegmentFactory segmentFactory) {
        this.id = id;
        this.movementSystem = ms;
        this.navigation = nav;
        this.powerManager = pm;
        this.communication = comm;
        this.knowledgeBase = kb;
        this.location = startLoc;
        this.destination = startLoc;
        this.segmentFactory = segmentFactory;
        this.currentSegment = segmentFactory.getMapSegment(startLoc);
        startIdle();

    }

    public Robot(Robot other) {
        this.id = other.id;   // оригинальный ID
        this.movementSystem = other.movementSystem.clone();
        this.navigation = other.navigation.clone();
        this.powerManager = other.powerManager.clone();
        this.communication = other.communication.clone();
        this.knowledgeBase = other.knowledgeBase.clone();
        this.currentTool = other.currentTool != null ? other.currentTool.clone() : null;
        this.location = other.location.clone();
        this.destination = other.destination != null ? other.destination.clone() : null;
        this.segmentFactory = other.segmentFactory;
        this.toolPool = other.toolPool;
        this.currentSegment = other.currentSegment; // неглубокое копирование, но достаточно
        if (other.currentState != null) {
            this.currentState = other.currentState.clone(this);
            this.currentState.enter();  //важно: войти в состояние, чтобы настроить ссылку на робота
        } else {
            startIdle(); //на случай, если состояние не задано
        }
    }

    public ToolPool getToolPool() {
        return toolPool;
    }

    public void setCurrentTool(ITool tool) {
        this.currentTool = tool;
    }

    public void setCurrentSegment(MapSegment segment) {
        this.currentSegment = segment;
    }

    @Override
    public void addRobotObserver(IRobotObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeRobotObserver(IRobotObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyRobotObservers(RobotEvent event) {
        for (IRobotObserver obs : observers) {
            obs.onRobotEvent(event);
        }
    }

    public void startIdle() {
        if (currentState != null) currentState.exit();
        currentState = new IdleState(this);
        currentState.enter();
    }

    public void startWorking() {
        if (currentState != null) currentState.exit();
        currentState = new WorkingState(this);
        currentState.enter();
    }

    public void startCharging() {
        if (currentState != null) currentState.exit();
        currentState = new ChargingState(this);
        currentState.enter();
        notifyRobotObservers(new RobotEvent(id, EventType.CHARGING_STARTED, null));
    }

    public void startMoving(Location dest) {
        this.destination = dest;
        if (currentState != null) currentState.exit();
        currentState = new MovingState(this);
        currentState.enter();
        notifyRobotObservers(new RobotEvent(id, EventType.MOVING_STARTED, dest));
    }

    public void handleError() {
        if (currentState != null) currentState.exit();
        currentState = new ErrorState(this);
        currentState.enter();
        notifyRobotObservers(new RobotEvent(id, EventType.ERROR, "Произошла ошибка"));
    }

    public void act() {
        if (currentState != null) {
            currentState.execute();
        }
    }

    public void resetError() {
        System.out.println(id + ": ошибка успешно сброшена");
        startIdle();
        notifyRobotObservers(new RobotEvent(id, EventType.ERROR_RESET, null));
    }

    @Override
    public Robot clone() {
        return new Robot(this);
    }

    public MapSegment getCurrentSegment() { return currentSegment; }

    @Override
    public String getRobotId() {
        return id;
    }

    @Override
    public Iterator<Object> iterator() {
        return new RobotComponentIterator(this);
    }

    @Override public RobotStatus getStatus() { return currentState.getStatus(); }

    @Override
    public void setTool(ITool tool) {
        if (canUseTool(tool)) {
            this.currentTool = tool;
        } else {
            handleError();
        }
    }

    @Override
    public boolean canUseTool(ITool tool) {
        return knowledgeBase.isToolCompatible(tool);
    }

    // Реализация геттеров
    public IMovementSystem getMovementSystem() { return movementSystem; }
    public INavigation getNavigation() { return navigation; }
    public PowerManager getPowerManager() { return powerManager; }
    public ICommunication getCommunication() { return communication; }
    public IKnowledgeBase<?> getKnowledgeBase() { return knowledgeBase; }
    public ITool getCurrentTool() { return currentTool; }
    public MapSegmentFactory getSegmentFactory() { return segmentFactory; }

    @Override
    public String toString() {
        String toolStr = (currentTool != null) ? currentTool.getName() : "не установлен";
        return String.format("Робот '%s': состояние %s, инструмент: %s",
                id, currentState.getStatus(), toolStr);
    }

    @Override
    public void setToolPool(ToolPool pool) {
        this.toolPool = pool;
    }

    public Location getDestination() {
        return destination;
    }
}

