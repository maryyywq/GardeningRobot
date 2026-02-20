abstract class Robot implements IRobot {
    protected String id; //Уникальный идентификатор робота
    protected RobotStatus status = RobotStatus.IDLE; //Текущий статус, по умолчанию IDLE
    protected IMovementSystem movementSystem; //Система передвижения
    protected INavigation navigation; //Навигационная система
    protected IPowerSource powerSource; //Источник питания
    protected ICommunication communication; //Система связи
    protected IKnowledgeBase<?> knowledgeBase; //База знаний
    protected ITool currentTool; //Текущий установленный инструмент
    protected Location location; //Текущее местоположение

    public Robot(String id, IMovementSystem ms, INavigation nav, IPowerSource ps,
                         ICommunication comm, IKnowledgeBase<?> kb, Location startLoc) {
        this.id = id;
        this.movementSystem = ms;
        this.navigation = nav;
        this.powerSource = ps;
        this.communication = comm;
        this.knowledgeBase = kb;
        this.location = startLoc;
    }

    @Override public void startTask() { status = RobotStatus.WORKING; System.out.println(id + ": задача запущена"); }
    @Override public void stopTask() { status = RobotStatus.IDLE; System.out.println(id + ": задача остановлена"); }
    @Override public RobotStatus getStatus() { return status; }
    @Override public void receiveCommand(String command) {
        System.out.println(id + ": получена команда: " + command);
        communication.receiveCommand(command); //Передаём команду в систему связи
        communication.sendData("Подтверждение", "контроллер");
        startTask();
    }

    @Override
    public void setTool(ITool tool) {
        if (canUseTool(tool)) {
            this.currentTool = tool;
            System.out.println(id + ": установлен инструмент " + tool.getName()); // вместо getClass().getSimpleName()
        } else {
            System.out.println(id + ": невозможно использовать инструмент " + tool.getName() + " - несовместим с моей базой знаний");
        }
    }

    @Override
    public boolean canUseTool(ITool tool) {
        return knowledgeBase.isToolCompatible(tool);
    }

    // Реализация геттеров
    @Override public IMovementSystem getMovementSystem() { return movementSystem; }
    @Override public INavigation getNavigation() { return navigation; }
    @Override public IPowerSource getPowerSource() { return powerSource; }
    @Override public ICommunication getCommunication() { return communication; }
    @Override public IKnowledgeBase<?> getKnowledgeBase() { return knowledgeBase; }
    @Override public ITool getCurrentTool() { return currentTool; }
}

