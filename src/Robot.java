import java.util.Iterator;

class Robot implements IRobot, Iterable<Object> , Prototype<Robot> {
    protected String id; //Уникальный идентификатор робота
    protected RobotStatus status = RobotStatus.IDLE; //Текущий статус, по умолчанию IDLE
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
        this.location = startLoc.clone();
        this.segmentFactory = segmentFactory;
        this.currentSegment = segmentFactory.getMapSegment(startLoc);
    }

    private Robot(Robot other, String newId) {
        this.id = newId;
        this.status = RobotStatus.IDLE; // сбрасываем статус
        // Глубокое копирование всех компонентов
        this.movementSystem = other.movementSystem.clone();
        this.navigation = other.navigation.clone();
        this.powerManager = other.powerManager.clone();
        this.communication = other.communication.clone();
        this.knowledgeBase = other.knowledgeBase.clone();
        this.currentTool = other.currentTool != null ? other.currentTool.clone() : null;
        this.location = other.location.clone();
        this.segmentFactory = other.segmentFactory; // фабрика разделяемая (Flyweight)
        this.currentSegment = segmentFactory.getMapSegment(this.location);
        this.toolPool = other.toolPool;
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
            startTask(); // использует currentTool
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
        // Добавляем временную метку для уникальности (можно использовать UUID)
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


    @Override
    public void startTask() {
        if (currentTool == null) {
            System.out.println(id + ": ошибка - инструмент не установлен");
            status = RobotStatus.ERROR;
            return;
        }

        double requiredEnergy = currentTool.getPowerConsumption();
        PowerAction action = powerManager.checkPower(requiredEnergy);

        switch (action) {
            case CONTINUE:
                status = RobotStatus.WORKING;
                System.out.println(id + ": задача запущена с инструментом " + currentTool.getName() +
                        " (потребление " + requiredEnergy + " ед.)");
                currentTool.execute();
                powerManager.consumeEnergy(requiredEnergy);
                break;
            case CHARGE:
                System.out.println(id + ": недостаточно энергии (нужно " + requiredEnergy +
                        "), отправляюсь на зарядку");
                status = RobotStatus.CHARGING;
                powerManager.charge();
                break;
            case USE_BACKUP:
                System.out.println(id + ": переключаюсь на резервный источник");
                powerManager.switchToBackup();
                break;
            case STOP:
                System.out.println(id + ": недостаточно энергии для инструмента " +
                        currentTool.getName() + ", задача отменена");
                status = RobotStatus.ERROR;
                break;
        }
    }

    @Override public void stopTask() { status = RobotStatus.IDLE; System.out.println(id + ": задача остановлена"); }
    @Override public RobotStatus getStatus() { return status; }

    @Override
    public void setTool(ITool tool) {
        if (canUseTool(tool)) {
            this.currentTool = tool;
            System.out.println(id + ": установлен инструмент " + tool.getName());
        } else {
            System.out.println(id + ": невозможно использовать инструмент " + tool.getName() + " - несовместим с моей базой знаний");
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
                id, status.getDescription(), toolStr);
    }

    @Override
    public void setToolPool(ToolPool pool) {
        this.toolPool = pool;
    }
}

