public class Main {
    public static void main(String[] args) {

        //Создаем компоненты
        IMovementSystem wheels = new WheeledMovement();
        IMovementSystem legs = new LeggedMovement();
        IMovementSystem heli = new HelicopterMovement();
        IMovementSystem plane = new PlaneMovement();
        INavigation gps = new GPSNavigation();
        INavigation vision = new VisionNavigation();
        ICommunication wifi = new WiFiCommunication();
        ICommunication lte = new LTECommunication();

        //Создаем базы знаний и записи в них
        WateringKnowledgeBase wateringKB = new WateringKnowledgeBase();
        wateringKB.addEntry("томат", new WateringEntry(2.5, "дождевание"));
        wateringKB.addEntry("огурец", new WateringEntry(3.0, "точечный"));

        FertilizingKnowledgeBase fertilizingKB = new FertilizingKnowledgeBase();
        fertilizingKB.addEntry("томат", new FertilizingEntry("NPK 10-10-10", 50));

        MedicalKnowledgeBase medicalKB = new MedicalKnowledgeBase();
        medicalKB.addEntry("фитофтора", new MedicalEntry("бордоская смесь", 100));

        PlantingKnowledgeBase plantingKnowledgeBase = new PlantingKnowledgeBase();
        plantingKnowledgeBase.addEntry("томат", new PlantingEntry(15, 15));

        //Создаем источники питания
        IPowerSource battery1 = new Battery();
        IPowerSource battery2 = new Battery();
        IPowerSource battery3 = new Battery();
        IPowerSource fuelCell1 = new FuelCell();
        IPowerSource fuelCell2 = new FuelCell();
        IPowerSource fuelCell3 = new FuelCell();

        //Создаем инструменты
        ITool wateringTool = new WateringTool();
        ITool fertilizingTool = new FertilizingTool();
        ITool medicalTool = new MedicalTool();
        ITool plantingTool = new PlantingTool();

        //Создаём фабрику сегментов (для Flyweight)
        MapSegmentFactory segmentFactory = new MapSegmentFactory();

        System.out.println("1. Демонстрация паттерна Bridge");
        System.out.println("1.1. Демонстрация менеджера энергопотребления:");

        //Создаем роботов
        Robot ecoBattery = new Robot("EcoBattery", wheels, gps, new EcoPowerManager(battery1), wifi, wateringKB, new Location(0, 0), segmentFactory);
        Robot ecoFuel = new Robot("EcoFuel", plane, gps, new EcoPowerManager(fuelCell1), wifi, plantingKnowledgeBase, new Location(0, 1), segmentFactory);

        Robot maxFuel = new Robot("MaxFuel", legs, vision, new MaxPerformanceManager(fuelCell2), lte, fertilizingKB, new Location(5, 5), segmentFactory);
        Robot maxBattery = new Robot("MaxBattery", legs, vision, new MaxPerformanceManager(battery2), lte, fertilizingKB, new Location(5, 6), segmentFactory);

        Robot balanceBattery = new Robot("BalanceBattery", heli, gps, new BalancePowerManager(battery3), lte, medicalKB, new Location(10, 10), segmentFactory);
        Robot balanceFuel = new Robot("BalanceFuel", heli, gps, new BalancePowerManager(fuelCell3), lte, medicalKB, new Location(10, 11), segmentFactory);


        System.out.println();

        //Устанавливаем инструменты
        ecoBattery.setTool(wateringTool);
        ecoFuel.setTool(plantingTool);
        maxFuel.setTool(fertilizingTool);
        maxBattery.setTool(fertilizingTool);
        balanceBattery.setTool(medicalTool);
        balanceFuel.setTool(medicalTool);

        System.out.println();

        //Экономный менеджер для батареи
        battery1.setLevel(30.0);
        System.out.println("Начальный заряд: " + battery1.getLevel() + "% (высокий)");
        ecoBattery.startTask();
        System.out.println("Заряд после задачи: " + battery1.getLevel() + "%\n");

        battery1.setLevel(24.0);
        System.out.println("Начальный заряд: " + battery1.getLevel() + "% (низкий)");
        ecoBattery.startTask();
        System.out.println("Заряд после (робот зарядился): " + battery1.getLevel() + "%\n");

        //Экономный менеджер для топлива
        fuelCell1.setLevel(30.0);
        System.out.println("Начальный уровень топлива: " + fuelCell1.getLevel() + "% (высокий)");
        ecoFuel.startTask(); // выполняется, уровень становится 25%
        System.out.println("Уровень после задачи: " + fuelCell1.getLevel() + "%\n");

        fuelCell1.setLevel(24.0);
        System.out.println("Начальный уровень топлива: " + fuelCell1.getLevel() + "% (низкий)");
        ecoFuel.startTask();
        System.out.println("Уровень после (заправился): " + fuelCell1.getLevel() + "%\n");

        //Менеджер с максимальной производительностью для топлива
        fuelCell2.setLevel(30.0);
        System.out.println("Начальный уровень топлива: " + fuelCell2.getLevel() + "% (высокий)");
        maxFuel.startTask();
        System.out.println("Уровень после задачи: " + fuelCell2.getLevel() + "%\n");

        fuelCell2.setLevel(7.0);
        System.out.println("Начальный уровень топлива: " + fuelCell2.getLevel() + "% (низкий)");
        maxFuel.startTask();
        System.out.println("Уровень после (переключился на резерв): " + fuelCell2.getLevel() + "%\n");

        //Менеджер с максимальной производительностью для батареи
        battery2.setLevel(30.0);
        System.out.println("Начальный заряд: " + battery2.getLevel() + "% (высокий)");
        maxBattery.startTask();
        System.out.println("Заряд после задачи: " + battery2.getLevel() + "%\n");

        battery2.setLevel(7.0);
        System.out.println("Начальный заряд: " + battery2.getLevel() + "% (низкий, без резерва)");
        maxBattery.startTask();
        System.out.println("Заряд после (задача не выполнена): " + battery2.getLevel() + "%\n");

        //Сбалансированный менедженр для батареи
        battery3.setLevel(20.0);
        System.out.println("Начальный заряд: " + battery3.getLevel() + "% (высокий)");
        balanceBattery.startTask();
        System.out.println("Заряд после задачи: " + battery3.getLevel() + "%\n");

        battery3.setLevel(11.0);
        System.out.println("Начальный заряд: " + battery3.getLevel() + "% (низкий, без резерва)");
        balanceBattery.startTask();
        System.out.println("Заряд после (зарядился): " + battery3.getLevel() + "%\n");

        //Сбалансированный менедженр для топлива
        fuelCell3.setLevel(20.0);
        System.out.println("Начальный уровень топлива: " + fuelCell3.getLevel() + "% (высокий)");
        balanceFuel.startTask();
        System.out.println("Уровень после задачи: " + fuelCell3.getLevel() + "%\n");

        fuelCell3.setLevel(11.0);
        System.out.println("Начальный уровень топлива: " + fuelCell3.getLevel() + "% (низкий, есть резерв)");
        balanceFuel.startTask();
        System.out.println("Уровень после (переключился на резерв): " + fuelCell3.getLevel() + "%\n");

        Location loc = new Location(10, 10);

        System.out.println("1.2. Демонстрация систем передвижения:");
        ecoBattery.moveTo(loc); //Колеса
        System.out.println();
        ecoFuel.moveTo(loc); //Самолет
        System.out.println();
        maxFuel.moveTo(loc); //Ножки
        System.out.println();
        balanceBattery.moveTo(loc);//Вертолет
        System.out.println();

        System.out.println("2. Демонстрация паттерна Flyweight (разделение сегментов карты):");

        //Используем один и тот же объект Location для демонстрации
        Location location = new Location(3, 3);

        MapSegment seg1 = segmentFactory.getMapSegment(location);
        MapSegment seg2 = segmentFactory.getMapSegment(location); //тот же объект
        System.out.println();

        System.out.println("Первый и второй сегменты для одинаковых координат: " + (seg1 == seg2)); //true

        System.out.println();
        //Изменяем один сегмент – изменения видны везде
        System.out.println("До изменения: " + seg1);
        seg1.setPlant("помидоры");
        seg1.setWatteringLevel(3.0);
        System.out.println("После изменения через первый сегмент: " + seg1);
        System.out.println("Второй сегмент: " + seg2);
        System.out.println();

        //Роботы получают сегменты при перемещении
        ecoBattery.moveTo(location);
        System.out.println(ecoBattery.getRobotId() + " переместился на " + location + ", его сегмент: " + ecoBattery.getCurrentSegment());
        balanceFuel.moveTo(location);
        System.out.println(balanceFuel.getRobotId() + " переместился на " + location + ", его сегмент: " + balanceFuel.getCurrentSegment());
        System.out.println("Оба робота ссылаются на один объект: " + (ecoBattery.getCurrentSegment() == balanceFuel.getCurrentSegment()));

        System.out.println("\n3. Демонстрация паттерна Facade (умный планшет садовода):");

        CentralController controller = new CentralController();
        controller.registerRobot(ecoBattery);
        controller.registerRobot(ecoFuel);
        controller.registerRobot(maxFuel);
        controller.registerRobot(maxBattery);
        controller.registerRobot(balanceBattery);
        controller.registerRobot(balanceFuel);


        GardenFacade garden = new GardenFacade(controller, segmentFactory);

        System.out.println("Выращиваем помидоры...");
        garden.growTomatoes(new Location(2, 3));
        System.out.println();

        System.out.println("Полив всех грядок...");
        garden.waterAllBeds();
        System.out.println();

        System.out.println("Удобрение всех грядок...");
        garden.fertilizeAllBeds();
        System.out.println();

        System.out.println("Собрать урожай со всех грядок...");
        garden.harvestAllBeds();

        System.out.println("\n4. Демонстрация паттерна Information Expert:");

        System.out.println("Проверка совместимости инструмента (этим занимается база знаний):");
        System.out.println(ecoBattery.getRobotId() + " может использовать инструмент для полива? " + ecoBattery.canUseTool(wateringTool));
        System.out.println(ecoBattery.getRobotId() + " может использовать инструмент для лечения? " + ecoBattery.canUseTool(medicalTool));
        System.out.println();
    }

}