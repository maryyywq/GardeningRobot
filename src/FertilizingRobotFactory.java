public class FertilizingRobotFactory implements RobotFactory {
    private MovementSystemFabric movementFab = new LeggedMovementFabric();
    private NavigationFabric navFab = new VisionNavigationFabric();
    private CommunicationFabric commFab = new LTECommunicationFabric();
    private PowerSourceFabric psFab = new FuelCellFabric();
    private PowerManagerFabric pmFab = new MaxPerformanceManagerFabric();
    private ToolFabric toolFab = new FertilizingToolFabric();

    @Override
    public Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory) {
        IMovementSystem movement = movementFab.create();
        INavigation navigation = navFab.create(startLoc);
        ICommunication communication = commFab.create();
        IPowerSource powerSource = psFab.create();
        PowerManager powerManager = pmFab.create(powerSource);
        ITool tool = toolFab.create();
        IKnowledgeBase<?> kb = new FertilizingKnowledgeBase();

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
