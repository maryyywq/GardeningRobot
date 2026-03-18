public class FertilizingRobotFactory implements RobotFactory {
    @Override
    public Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory) {
        IMovementSystem movement = new LeggedMovementFabric().create();
        INavigation navigation = new VisionNavigationFabric().create(startLoc);
        ICommunication communication = new LTECommunicationFabric().create();
        IPowerSource ps = new FuelCellFabric().create();
        PowerManager pm = new MaxPerformanceManagerFabric().create(ps);
        IKnowledgeBase<?> kb = new FertilizingKnowledgeBase();
        return new Robot(id, movement, navigation, pm, communication, kb, startLoc, segmentFactory);
    }
}