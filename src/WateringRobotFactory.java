public class WateringRobotFactory implements RobotFactory {
    @Override
    public Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory) {
        //Создаём компоненты через фабрики (Factory Method)
        IMovementSystem movement = new WheeledMovementFabric().create();
        INavigation navigation = new GPSNavigationFabric().create(startLoc);
        ICommunication communication = new WiFiCommunicationFabric().create();
        IPowerSource ps = new BatteryFabric().create();
        PowerManager pm = new EcoPowerManagerFabric().create(ps);
        IKnowledgeBase<?> kb = new WateringKnowledgeBase();

        // Робот создаётся без инструмента!
        return new Robot(id, movement, navigation, pm, communication, kb, startLoc, segmentFactory);
    }
}