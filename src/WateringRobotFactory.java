public class WateringRobotFactory implements RobotFactory {
    private MovementSystemFabric movementFab = new WheeledMovementFabric();
    private NavigationFabric navFab = new GPSNavigationFabric();
    private CommunicationFabric commFab = new WiFiCommunicationFabric();
    private PowerSourceFabric psFab = new BatteryFabric();
    private PowerManagerFabric pmFab = new EcoPowerManagerFabric();
    private ToolFabric toolFab = new WateringToolFabric();

    @Override
    public Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory) {
        //Создание компонентов через фабрики
        IMovementSystem movement = movementFab.create();
        INavigation navigation = navFab.create(startLoc);
        ICommunication communication = commFab.create();
        IPowerSource powerSource = psFab.create();
        PowerManager powerManager = pmFab.create(powerSource);
        ITool tool = toolFab.create();
        IKnowledgeBase<?> kb = new WateringKnowledgeBase();

        //Сборка робота
        Robot robot = new Robot(
                id,
                movement,
                navigation,
                powerManager,
                communication,
                kb,
                startLoc,
                segmentFactory
        );
        robot.setTool(tool);
        return robot;
    }
}
