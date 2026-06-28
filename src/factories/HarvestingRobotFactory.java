package factories;

import components.knowledge.HarvestingKnowledgeBase;
import components.power.PowerManager;
import core.*;
import map.MapSegmentFactory;
import models.Location;
import robot.Robot;

public class HarvestingRobotFactory implements RobotFactory {
    @Override
    public Robot createRobot(String id, Location startLoc, MapSegmentFactory segmentFactory) {
        IMovementSystem movement = new HelicopterMovementFabric().create();
        INavigation navigation = new GPSNavigationFabric().create(startLoc);
        ICommunication communication = new WiFiCommunicationFabric().create();
        IPowerSource ps = new FuelCellFabric().create();
        PowerManager pm = new BalancePowerManagerFabric().create(ps);
        IKnowledgeBase<?> kb = new HarvestingKnowledgeBase();
        return new Robot(id, movement, navigation, pm, communication, kb, startLoc, segmentFactory);
    }
}