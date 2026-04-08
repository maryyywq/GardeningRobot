import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("1. Тестирование паттерна Command:\n");
        CentralController controller = CentralController.getInstance();
        MapSegmentFactory segmentFactory = new MapSegmentFactory();
        controller.addToolToPool(new WateringTool());
        controller.addToolToPool(new FertilizingTool());
        controller.addToolToPool(new WeedingTool());
        controller.addToolToPool(new HarvestingTool());
        controller.addToolToPool(new MowingTool());
        controller.addToolToPool(new MedicalTool());
        controller.addToolToPool(new PlantingTool());
        System.out.println();

        //Создаём специализированных роботов
        Robot wateringRobot = new WateringRobotFactory().createRobot("WaterBot", new Location(0, 0), segmentFactory);
        Robot fertilizingRobot = new FertilizingRobotFactory().createRobot("FertilizeBot", new Location(1, 1), segmentFactory);
        Robot weedingRobot = new Robot(
                "WeedBot",
                new WheeledMovementFabric().create(),
                new GPSNavigationFabric().create(new Location(2,2)),
                new EcoPowerManagerFabric().create(new BatteryFabric().create()),
                new WiFiCommunicationFabric().create(),
                new WeedingKnowledgeBase(),
                new Location(2,2),
                segmentFactory
        );

        Robot harvestingRobot = new HarvestingRobotFactory().createRobot("HarvestBot", new Location(3, 3), segmentFactory);
        Robot mowingRobot = new Robot(
                "MowBot",
                new WheeledMovementFabric().create(),
                new GPSNavigationFabric().create(new Location(4,4)),
                new EcoPowerManagerFabric().create(new BatteryFabric().create()),
                new WiFiCommunicationFabric().create(),
                new MowingKnowledgeBase(),
                new Location(4,4),
                segmentFactory
        );
        Robot medicalRobot = new Robot(
                "MedicalBot",
                new WheeledMovementFabric().create(),
                new GPSNavigationFabric().create(new Location(5,5)),
                new EcoPowerManagerFabric().create(new BatteryFabric().create()),
                new WiFiCommunicationFabric().create(),
                new MedicalKnowledgeBase(),
                new Location(5,5),
                segmentFactory
        );
        Robot plantingRobot = new Robot(
                "PlantBot",
                new WheeledMovementFabric().create(),
                new GPSNavigationFabric().create(new Location(6,6)),
                new EcoPowerManagerFabric().create(new BatteryFabric().create()),
                new WiFiCommunicationFabric().create(),
                new PlantingKnowledgeBase(),
                new Location(6,6),
                segmentFactory
        );
        //Регистрируем всех роботов в контроллере
        controller.registerRobot(wateringRobot);
        controller.registerRobot(fertilizingRobot);
        controller.registerRobot(weedingRobot);
        controller.registerRobot(harvestingRobot);
        controller.registerRobot(mowingRobot);
        controller.registerRobot(medicalRobot);
        controller.registerRobot(plantingRobot);
        System.out.println();

        ICommand waterCmd = new WaterCommand(wateringRobot, 5.0);
        controller.assignCommand(wateringRobot.getRobotId(), waterCmd);
        System.out.println();

        ICommand fertilizeCmd = new FertilizeCommand(fertilizingRobot, "аммиачная селитра");
        controller.assignCommand(fertilizingRobot.getRobotId(), fertilizeCmd);
        System.out.println();

        ICommand weedCmd = new WeedCommand(weedingRobot);
        controller.assignCommand(weedingRobot.getRobotId(), weedCmd);
        System.out.println();

        ICommand harvestCmd = new HarvestCommand(harvestingRobot);
        controller.assignCommand(harvestingRobot.getRobotId(), harvestCmd);
        System.out.println();

        ICommand mowCmd = new MowCommand(mowingRobot, 3.5);
        controller.assignCommand(mowingRobot.getRobotId(), mowCmd);
        System.out.println();

        ICommand treatCmd = new TreatCommand(medicalRobot, "фитоспорин");
        controller.assignCommand(medicalRobot.getRobotId(), treatCmd);
        System.out.println();

        ICommand plantCmd = new PlantCommand(medicalRobot, PlantType.TOMATO);
        controller.assignCommand(medicalRobot.getRobotId(), plantCmd);
        System.out.println();

        ICommand moveCmd = new MoveCommand(wateringRobot, new Location(100, 200));
        controller.assignCommand(harvestingRobot.getRobotId(), moveCmd);
        System.out.println();

        ICommand chargeCmd = new ChargeCommand(fertilizingRobot);
        controller.assignCommand(fertilizingRobot.getRobotId(), chargeCmd);

    }
}




