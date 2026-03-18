import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Демонстрация паттернов: \n");
        System.out.println("1. Factory Method — создание компонентов:");

        //Конкретные фабрики
        MovementSystemFabric wheeledFab = new WheeledMovementFabric();
        NavigationFabric gpsFab = new GPSNavigationFabric();
        CommunicationFabric wifiFab = new WiFiCommunicationFabric();
        PowerSourceFabric batteryFab = new BatteryFabric();
        PowerManagerFabric ecoFab = new EcoPowerManagerFabric();
        ToolFabric wateringFab = new WateringToolFabric();

        //Создание объектов через фабрики
        IMovementSystem wheels = wheeledFab.create();
        System.out.println("Создана система передвижения: " + wheels);

        //Навигация с локацией по умолчанию (0,0)
        INavigation gpsDefault = gpsFab.create();
        System.out.println("Навигация (по умолчанию): " + gpsDefault);

        //Навигация с другой локацией
        INavigation gpsCustom = gpsFab.create(new Location(15, 25));
        System.out.println("Навигация (пользовательская): " + gpsCustom);

        ICommunication wifi = wifiFab.create();
        System.out.println("Связь: " + wifi);

        IPowerSource battery = batteryFab.create();
        System.out.println("Источник питания: " + battery);

        PowerManager ecoManager = ecoFab.create(battery);
        System.out.println("Менеджер питания: " + ecoManager);

        ITool wateringTool = wateringFab.create();
        System.out.println("Инструмент: " + wateringTool);
        System.out.println();


        System.out.println("2. Abstract Factory — создание специализированных роботов:");

        MapSegmentFactory segmentFactory = new MapSegmentFactory();

        //Фабрики роботов (каждая создаёт робота с определённым набором компонентов)
        RobotFactory wateringRobotFactory = new WateringRobotFactory();
        RobotFactory fertilizingRobotFactory = new FertilizingRobotFactory();
        RobotFactory harvestingRobotFactory = new HarvestingRobotFactory();

        Robot waterBot = wateringRobotFactory.createRobot("WaterBot-1", new Location(10, 10), segmentFactory);
        Robot fertBot = fertilizingRobotFactory.createRobot("FertiBot-1", new Location(5, 5), segmentFactory);
        Robot harvestBot = harvestingRobotFactory.createRobot("HarvestBot-1", new Location(0, 0), segmentFactory);
        System.out.println();

        System.out.println("Созданы роботы:");
        System.out.println(waterBot);
        System.out.println(fertBot);
        System.out.println(harvestBot);
        System.out.println();

        System.out.println("Компоненты роботов (через итератор):");
        for (Robot robot : Arrays.asList(waterBot, fertBot, harvestBot)) {
            System.out.println("Робот: " + robot.getRobotId());
            Iterator<Object> it = robot.iterator();
            int componentNumber = 1;
            while (it.hasNext()) {
                Object component = it.next();
                System.out.println("    Компонент " + componentNumber + ": " + component);
                componentNumber++;
            }
            System.out.println();
        }

        System.out.println("3. Singleton — центральный контроллер:");
        CentralController controller1 = CentralController.getInstance();
        CentralController controller2 = CentralController.getInstance();

        System.out.println("controller1 == controller2? " + (controller1 == controller2)); // true

        controller1.registerRobot(waterBot);
        controller1.registerRobot(fertBot);
        controller1.registerRobot(harvestBot);

        System.out.println("Роботы зарегистрированы. Количество роботов в контроллере 1: " +
                controller1.getAllRobots().size());
        System.out.println("Роботы зарегистрированы. Количество роботов в контроллере 2: " +
                controller2.getAllRobots().size());
        System.out.println();


        System.out.println("4. Prototype — клонирование робота:");

        //Для демонстрации клонирования адаптера создадим робота со старым адаптером
        OldWateringSystem oldSystem = new OldWateringSystem(10.0, 2.0);
        ITool adaptedTool = new OldWateringAdapter(oldSystem);
        Robot adapterRobot = new Robot(
                "AdapterBot",
                new WheeledMovementFabric().create(),
                new GPSNavigationFabric().create(new Location(0,0)),
                new EcoPowerManagerFabric().create(new BatteryFabric().create()),
                new WiFiCommunicationFabric().create(),
                new WateringKnowledgeBase(),
                new Location(1,1),
                segmentFactory
        );
        adapterRobot.setTool(adaptedTool);
        System.out.println("Робот с адаптером: " + adapterRobot);

        Robot clonedAdapterRobot = adapterRobot.clone();
        System.out.println();
        System.out.println("Клон робота с адаптером: " + clonedAdapterRobot);
        System.out.println("ID оригинала: " + adapterRobot.getRobotId() + ", ID клона: " + clonedAdapterRobot.getRobotId());
        System.out.println();

        //Клонирование обычного робота
        Robot clonedBot = waterBot.clone();
        System.out.println("Оригинал WaterBot: " + waterBot);
        System.out.println("Клон WaterBot:     " + clonedBot);
        System.out.println();

        //Проверяем независимость
        waterBot.startTask();
        System.out.println("После startTask() оригинала:");
        System.out.println("Статус оригинала: " + waterBot.getStatus());
        System.out.println("Статус клона:     " + clonedBot.getStatus());
        System.out.println();


        System.out.println("5. Object Pool — пул инструментов:");
        System.out.println("Добавляем инструменты в пул контроллера:\n");

        controller1.addToolToPool(new WateringToolFabric().create());
        controller1.addToolToPool(new WateringToolFabric().create());
        controller1.addToolToPool(new WateringToolFabric().create()); //3 поливалки
        controller1.addToolToPool(new FertilizingToolFabric().create());
        controller1.addToolToPool(new FertilizingToolFabric().create()); //2 удобрялки

        ITool harvestTool = new HarvestingToolFabric().create();
        controller1.addToolToPool(harvestTool); //1 сборщик
        System.out.println();

        controller1.registerRobot(waterBot);
        controller1.registerRobot(fertBot);
        controller1.registerRobot(harvestBot);
        System.out.println();

        //Посылаем задачи роботам
        controller1.assignTask(waterBot.getRobotId(), new Task("WATER", Map.of()));
        System.out.println();
        controller1.assignTask(waterBot.getRobotId(), new Task("WATER", Map.of())); //второй полив
        System.out.println();
        controller1.assignTask(fertBot.getRobotId(), new Task("FERTILIZE", Map.of()));
        System.out.println();
        controller1.assignTask(harvestBot.getRobotId(), new Task("HARVEST", Map.of())); //первая попытка сбора (должна выполниться)
        System.out.println();

        //Удаляем инструмент для сбора из пула
        System.out.println("Удаляем инструмент для сбора из пула...");
        controller1.removeToolFromPool(harvestTool);

        //Пытаемся дать задачу на сбор снова
        controller1.assignTask(harvestBot.getRobotId(), new Task("HARVEST", Map.of())); // должна не выполниться



    }
}