public class HarvestingRobotFactory implements RobotFactory {
    private MovementSystemFabric movementFab = new HelicopterMovementFabric();
    private NavigationFabric navFab = new GPSNavigationFabric();
    private CommunicationFabric commFab = new WiFiCommunicationFabric();
    private PowerSourceFabric psFab = new FuelCellFabric();
    private PowerManagerFabric pmFab = new BalancePowerManagerFabric();
    private ToolFabric toolFab = new HarvestingToolFabric();

    @Override
    public Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory) {
        IMovementSystem movement = movementFab.create();
        INavigation navigation = navFab.create(startLoc);
        ICommunication communication = commFab.create();
        IPowerSource powerSource = psFab.create();
        PowerManager powerManager = pmFab.create(powerSource);
        ITool tool = toolFab.create();
        IKnowledgeBase<?> kb = new HarvestingKnowledgeBase();

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
