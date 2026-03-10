import java.util.Iterator;

class Robot implements IRobot, Iterable<Object> {
    protected String id; //Уникальный идентификатор робота
    protected RobotStatus status = RobotStatus.IDLE; //Текущий статус, по умолчанию IDLE
    protected IMovementSystem movementSystem; //Система передвижения
    protected INavigation navigation; //Навигационная система
    protected PowerManager powerManager;
    protected ICommunication communication; //Система связи
    protected IKnowledgeBase<?> knowledgeBase; //База знаний
    protected ITool currentTool; //Текущий установленный инструмент
    protected Location location; //Текущее местоположение

    public Robot() { }

    public Robot(String id, IMovementSystem ms, INavigation nav, PowerManager pm,
                         ICommunication comm, IKnowledgeBase<?> kb, Location startLoc) {
        this.id = id;
        this.movementSystem = ms;
        this.navigation = nav;
        this.powerManager = pm;
        this.communication = comm;
        this.knowledgeBase = kb;
        this.location = startLoc;
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
                status = RobotStatus.WORKING;
                currentTool.execute();
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
}

