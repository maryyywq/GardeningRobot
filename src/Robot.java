import java.util.Iterator;

class Robot implements IRobot, Iterable<Object> , Prototype<Robot> {
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

        startIdle(); // начальное состояние
    }

    // Конструктор клонирования
    private Robot(Robot other, String newId) {
        this.id = newId;
        this.movementSystem = other.movementSystem.clone();
        this.navigation = other.navigation.clone();
        this.powerManager = other.powerManager.clone();
        this.communication = other.communication.clone();
        this.knowledgeBase = other.knowledgeBase.clone();
        this.location = other.location;
        this.destination = other.destination;
        this.currentTool = other.currentTool != null ? other.currentTool.clone() : null;
        this.segmentFactory = other.segmentFactory;
        startIdle();
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
    }

    public void startMoving(Location dest) {
        this.destination = dest;
        if (currentState != null) currentState.exit();
        currentState = new MovingState(this);
        currentState.enter();
    }

    public void handleError() {
        if (currentState != null) currentState.exit();
        currentState = new ErrorState(this);
        currentState.enter();
    }

    public void act() {
        if (currentState != null) {
            currentState.execute();
        }
    }

    public void resetError() {
        System.out.println(id + ": ошибка успешно сброшена");
        startIdle();
    }


    private ToolType commandToToolType(String command) {
        if (command == null) return null;
        switch (command) {
            case "WATER":
                return ToolType.WATERING;
            case "FERTILIZE":
                return ToolType.FERTILIZING;
            case "TREAT":
                return ToolType.MEDICAL;
            case "PLANT":
                return ToolType.PLANTING;
            case "HARVEST":
                return ToolType.HARVESTING;
            case "WEED":
                return ToolType.WEEDING;
            case "MOW":
                return ToolType.MOWING;
            default:
                return null;
        }
    }

    @Override
    public void receiveCommand(String command) {
        if (toolPool == null) {
            System.out.println(id + ": ошибка - не привязан к пулу инструментов");
            return;
        }

        ToolType requiredType = commandToToolType(command);
        if (requiredType == null) {
            System.out.println(id + ": неизвестная команда '" + command + "'");
            return;
        }

        // Запрашиваем инструмент из пула
        ITool tool = toolPool.acquireTool(requiredType);
        if (tool == null) {
            System.out.println(id + ": нет свободного инструмента для " + requiredType);
            return;
        }

        if (!canUseTool(tool)) {
            System.out.println(id + ": команда несовместима с базой знаний, требуемый тип " + requiredType);
            return;
        }

        this.currentTool = tool; // временно устанавливаем

        try {
            System.out.println(id + ": получена команда: " + command);
            communication.receiveCommand(command);
            communication.sendData("Подтверждение", "контроллер");
            act();
        } finally {
            // Возвращаем инструмент в пул
            if (currentTool != null) {
                toolPool.releaseTool(currentTool);
                this.currentTool = null;
            }
        }
    }


    @Override
    public Robot clone() {
        String newId = generateUniqueId(this.id);
        return new Robot(this, newId);
    }

    private String generateUniqueId(String originalId) {
        return originalId + "_copy_" + System.currentTimeMillis();
    }

    public void moveTo(Location newLocation) {
        movementSystem.moveTo(newLocation);
        this.location = newLocation;
        this.currentSegment = segmentFactory.getMapSegment(newLocation);
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

    public void setDestination(Location destination) {
        this.destination = destination;
    }


}

