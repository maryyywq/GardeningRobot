import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Тестирование паттерна Visitor:\n");
        CentralController controller = CentralController.getInstance();
        MapSegmentFactory segmentFactory = new MapSegmentFactory();

        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(1, 1);
        System.out.println();

        segmentFactory.getMapSegment(loc1).setSoil(SoilType.SAND);
        segmentFactory.getMapSegment(loc2).setSoil(SoilType.CHERNOZEM);
        System.out.println();

        Robot waterBot = new WateringRobotFactory().createRobot("WaterBot", loc1, segmentFactory);
        Robot fertilizeBot = new FertilizingRobotFactory().createRobot("FertilizeBot", loc2, segmentFactory);
        System.out.println();

        controller.registerRobot(waterBot);
        controller.registerRobot(fertilizeBot);
        controller.addToolToPool(new WateringTool());
        controller.addToolToPool(new FertilizingTool());
        controller.addToolToPool(new WeedingTool());
        controller.addToolToPool(new HarvestingTool());
        System.out.println();

        System.out.println("Выполнение команд на назначенных сегментах:");
        controller.assignCommand("WaterBot", new WaterCommand(5.0));
        System.out.println();
        controller.assignCommand("FertilizeBot", new FertilizeCommand("компост"));
        System.out.println();

        System.out.println("Перемещение на другие сегменты и повторные действия:");
        controller.assignCommand("WaterBot", new MoveCommand(loc2));
        System.out.println();
        controller.assignCommand("WaterBot", new WaterCommand(3.0));
        System.out.println();

        controller.assignCommand("FertilizeBot", new MoveCommand(loc1));
        System.out.println();
        controller.assignCommand("FertilizeBot", new FertilizeCommand("азотное"));
        System.out.println();
        controller.assignCommand("WaterBot", new MoveCommand(loc1));
        System.out.println();
        System.out.println("Статистика посещений:");
        controller.getVisitor().printStatistics();
        System.out.println();

        System.out.println("2. Тестирование паттерна Command:\n");
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
        System.out.println();
        //Регистрируем всех роботов в контроллере
        controller.registerRobot(wateringRobot);
        controller.registerRobot(fertilizingRobot);
        controller.registerRobot(weedingRobot);
        controller.registerRobot(harvestingRobot);
        controller.registerRobot(mowingRobot);
        controller.registerRobot(medicalRobot);
        controller.registerRobot(plantingRobot);
        System.out.println();

        ICommand waterCmd = new WaterCommand(5.0);
        controller.assignCommand(wateringRobot.getRobotId(), waterCmd);
        System.out.println();

        ICommand fertilizeCmd = new FertilizeCommand("аммиачная селитра");
        controller.assignCommand(fertilizingRobot.getRobotId(), fertilizeCmd);
        System.out.println();

        ICommand weedCmd = new WeedCommand();
        controller.assignCommand(weedingRobot.getRobotId(), weedCmd);
        System.out.println();

        ICommand harvestCmd = new HarvestCommand();
        controller.assignCommand(harvestingRobot.getRobotId(), harvestCmd);
        System.out.println();

        ICommand mowCmd = new MowCommand(3.5);
        controller.assignCommand(mowingRobot.getRobotId(), mowCmd);
        System.out.println();

        ICommand treatCmd = new TreatCommand("фитоспорин");
        controller.assignCommand(medicalRobot.getRobotId(), treatCmd);
        System.out.println();

        ICommand plantCmd = new PlantCommand(PlantType.TOMATO);
        controller.assignCommand(medicalRobot.getRobotId(), plantCmd);
        System.out.println();

        ICommand moveCmd = new MoveCommand(new Location(100, 200));
        controller.assignCommand(wateringRobot.getRobotId(), moveCmd);
        System.out.println();

        ICommand chargeCmd = new ChargeCommand();
        controller.assignCommand(fertilizingRobot.getRobotId(), chargeCmd);
        System.out.println();

        System.out.println("3. Тестирование паттерна Chain of resposibility:\n");
        controller.clearRobots();
        System.out.println();
        controller.registerRobot(fertilizingRobot);
        controller.registerRobot(harvestingRobot);
        controller.registerRobot(wateringRobot);
        System.out.println();
        controller.executeCommand(waterCmd);
        System.out.println();

        controller.clearRobots();
        System.out.println();
        controller.registerRobot(medicalRobot);
        controller.registerRobot(harvestingRobot);
        System.out.println();
        controller.executeCommand(treatCmd);
        System.out.println();

        controller.clearRobots();
        controller.registerRobot(medicalRobot);
        controller.registerRobot(harvestingRobot);
        System.out.println();
        controller.executeCommand(waterCmd);
        System.out.println();

    }
}




