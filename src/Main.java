import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тестирование системы роботов-садовников: ");

        //Создание компонентов
        IMovementSystem wheels = new WheeledMovement();
        IMovementSystem legs = new LeggedMovement();
        IMovementSystem heli = new HelicopterMovement();
        IMovementSystem plain = new PlainMovement();
        INavigation gps = new GPSNavigation();
        INavigation vision = new VisionNavigation();
        IPowerSource battery = new Battery();
        IPowerSource fuelCell = new FuelCell();
        ICommunication wifi = new WiFiCommunication();
        ICommunication lte = new LTECommunication();


        //Заполнение базы знаний
        WateringKnowledgeBase wateringKB = new WateringKnowledgeBase();
        wateringKB.addEntry("томат", new WateringEntry(2.5, "дождевание"));
        wateringKB.addEntry("огурец", new WateringEntry(3.0, "точечный"));
        wateringKB.addEntry("газон", new WateringEntry(5.0, "линейный"));

        FertilizingKnowledgeBase fertilizingKB = new FertilizingKnowledgeBase();
        fertilizingKB.addEntry("томат", new FertilizingEntry("NPK 10-10-10", 50));
        fertilizingKB.addEntry("огурец", new FertilizingEntry("азотное", 30));

        WeedingKnowledgeBase weedingKB = new WeedingKnowledgeBase();
        weedingKB.addEntry("морковка", new WeedingEntry("механический", ""));
        weedingKB.addEntry("лук", new WeedingEntry("химический", "глифосат"));

        MowingKnowledgeBase mowingKB = new MowingKnowledgeBase();
        mowingKB.addEntry("газон", new MowingEntry(5.0, ""));
        mowingKB.addEntry("куст", new MowingEntry(0, "шар"));

        MedicalKnowledgeBase medicalKB = new MedicalKnowledgeBase();
        medicalKB.addEntry("фитофтора", new MedicalEntry("бордоская смесь", 100));
        medicalKB.addEntry("мучнистая роса", new MedicalEntry("раствор серы", 50));

        PlantingKnowledgeBase plantingKB = new PlantingKnowledgeBase();
        plantingKB.addEntry("томат", new PlantingEntry(40, 5));
        plantingKB.addEntry("морковь", new PlantingEntry(10, 2));

        HarvestingKnowledgeBase harvestingKB = new HarvestingKnowledgeBase();
        harvestingKB.addEntry("томат", new HarvestingEntry("красный", "механизированный"));
        harvestingKB.addEntry("яблоко", new HarvestingEntry("желто-красный", "ручной"));

        GroundRobot groundWateringBot = new GroundRobot("R2D2", wheels, gps, battery, wifi, wateringKB, new Location(0, 0));
        GroundRobot groundFertilizingBot = new GroundRobot("C3PO", legs, vision, fuelCell, lte, fertilizingKB, new Location(5, 5));

        Drone medicalDrone = new Drone("MediDrone", heli, gps, battery, lte, medicalKB, new Location(10, 10));

        Drone mowingDrone = new Drone("MowDrone", plain, vision, fuelCell, wifi, mowingKB, new Location(20, 20));

        IRobot proxyWatering = new RobotProxy(groundWateringBot);
        IRobot proxyFertilizing = new RobotProxy(groundFertilizingBot);
        IRobot proxyMedical = new RobotProxy(medicalDrone);
        IRobot proxyMowing = new RobotProxy(mowingDrone);

        ITool wateringTool = new WateringTool();
        ITool fertilizingTool = new FertilizingTool();
        ITool mowingTool = new MowingTool();
        ITool medicalTool = new MedicalTool();
        System.out.println();

        System.out.println("1. Тестирование установки инструментов роботам: ");
        System.out.println("Установка для робота-поливальщика инструмента для полива: ");
        proxyWatering.setTool(wateringTool);
        System.out.println();
        System.out.println("Установка для робота-поливальщика инструмента для лечения: ");
        proxyWatering.setTool(medicalTool);
        System.out.println();
        System.out.println("Установка для робота-удобрителя инструмента для удобрения: ");
        proxyFertilizing.setTool(fertilizingTool);
        System.out.println();
        System.out.println("Установка для робота-доктора инструмента для лечения: ");
        proxyMedical.setTool(medicalTool);
        System.out.println();
        System.out.println("Установка для робота-доктора инструмента для полива: ");
        proxyMedical.setTool(wateringTool);
        System.out.println();
        System.out.println("Установка для робота-парикмахера инструмента для стрижки: ");
        proxyMowing.setTool(mowingTool);
        System.out.println();

        System.out.println("2. Тестирование установки команд через proxy:");
        proxyWatering.receiveCommand("MOVE 10 20");
        System.out.println();
        proxyWatering.receiveCommand("SCAN");
        System.out.println();
        proxyWatering.receiveCommand("ATTACK");
        System.out.println();
        proxyWatering.receiveCommand("STATUS");
        System.out.println();

        System.out.println("3. Тестирование навигационных систем:");
        Location start = new Location(0, 0);
        Location goal = new Location(15, 15);
        gps.updatePosition();
        Route route = gps.planRoute(start, goal);
        System.out.println("GPS: спланирован маршрут: " + route.points);
        gps.adjustRoute(new Obstacle(new Location(5, 5)));
        System.out.println();


        System.out.println("4. Тестирование систем передвижения: ");
        groundWateringBot.getMovementSystem().moveTo(new Location(2, 3));
        groundWateringBot.getMovementSystem().setSpeed(1.5);
        groundWateringBot.getMovementSystem().stop();
        System.out.println();


        System.out.println("5. Тестирование систем связи: ");
        wifi.connect();
        wifi.sendData("Hello", "controller");
        String cmd = wifi.receiveCommand("MOVE 10 20");
        System.out.println("WiFi: получена команда: " + cmd);
        wifi.disconnect();
        System.out.println();


        System.out.println("6. Тестирование источников питания:");
        System.out.println("Аккумулятор: уровень " + battery.getLevel() + "%");
        battery.charge();
        System.out.println("Топливный элемент: уровень " + fuelCell.getLevel() + "%");
        fuelCell.switchToBackup();
        System.out.println();


        System.out.println("7. Центральный контроллер (назначение задач роботам):");
        CentralController controller = new CentralController();
        controller.registerRobot("R2D2", proxyWatering);
        controller.registerRobot("C3PO", proxyFertilizing);
        controller.registerRobot("MediDrone", proxyMedical);
        controller.registerRobot("MowDrone", proxyMowing);

        Map<String, Object> waterParams = new HashMap<>();
        waterParams.put("plant", "томат");
        Task waterTask = new Task("WATER", waterParams);
        controller.assignTask("R2D2", waterTask);
        System.out.println();

        Map<String, Object> fertilizeParams = new HashMap<>();
        fertilizeParams.put("plant", "огурец");
        Task fertilizeTask = new Task("HARVEST", fertilizeParams);
        controller.assignTask("C3PO", fertilizeTask);
        System.out.println();


        System.out.println("8. Тестирование proxy (робот без инструмента): ");
        GroundRobot noToolBot = new GroundRobot("NoTool", wheels, gps, battery, wifi, wateringKB, new Location(0, 0));
        IRobot proxyNoTool = new RobotProxy(noToolBot);
        controller.registerRobot("NoTool", proxyNoTool);
        controller.assignTask("NoTool", waterTask);
        System.out.println();


        System.out.println("9. Тестирование proxy (низкий заряд):");
        Battery lowBattery = new Battery();
        lowBattery.setLevel(5.0);
        GroundRobot lowBot = new GroundRobot("LowBot", wheels, gps, lowBattery, wifi, wateringKB, new Location(0, 0));
        IRobot proxyLowBot = new RobotProxy(lowBot);
        proxyLowBot.setTool(wateringTool);
        controller.registerRobot("LowBot", proxyLowBot);
        controller.assignTask("LowBot", waterTask);
        System.out.println();


        System.out.println("10. Мониторинг состояния роботов:");
        Map<String, RobotStatus> statuses = controller.monitorRobots();
        System.out.println();
        System.out.println("Состояния роботов:");
        for (Map.Entry<String, RobotStatus> entry : statuses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();


        System.out.println("11. Ручное изменение статуса:");
        groundWateringBot.startTask();
        System.out.println("R2D2 статус: " + groundWateringBot.getStatus());
        groundWateringBot.stopTask();
        System.out.println("R2D2 статус: " + groundWateringBot.getStatus());
        System.out.println();


        System.out.println("12. Тестирование инструментов:");
        Map<String, Object> toolParams = new HashMap<>();
        toolParams.put("volume", 10);
        toolParams.put("mode", "капельный");
        wateringTool.execute(toolParams);
        System.out.println("Статус инструмента после выполнения: " + wateringTool.getStatus());
        System.out.println();


        System.out.println("13. Получение информации из баз знаний:");
        WateringEntry we = wateringKB.getEntry("томат");
        System.out.println("Для томата: ");
        System.out.println(we.getInfo());
        System.out.println();
        MedicalEntry me = medicalKB.getEntry("фитофтора");
        System.out.println("Для фитофторы: ");
        System.out.println(me.getInfo());
        System.out.println();
        MowingEntry mowe = mowingKB.getEntry("куст");
        System.out.println("Для куста: ");
        System.out.println(mowe.getInfo());
        System.out.println();


        System.out.println("14. Проверка на возможность использования инструментов:");
        System.out.println("R2D2 может использовать лейку? ");
        System.out.println(proxyWatering.canUseTool(wateringTool));
        System.out.println("R2D2 может использовать медикаменты? ");
        System.out.println(proxyWatering.canUseTool(medicalTool));
        System.out.println();


        System.out.println("15. Проверка передачи данных с помощью сотовой связи:");
        lte.connect();
        lte.sendData("ping", "R2D2");
        lte.receiveCommand("START");
        lte.disconnect();
        System.out.println();

        System.out.println("16. Проверка навигации с помощью компьютерного зрения:");
        vision.updatePosition();
        vision.planRoute(new Location(0, 0), new Location(5, 5));
        vision.adjustRoute(new Obstacle(new Location(2, 2)));
        System.out.println();


        System.out.println("17. Проверка совместимости типа робота с типом передвижения:");
        try {
            GroundRobot brokenBot = new GroundRobot("Broken", heli, gps, battery, wifi, wateringKB, new Location(0, 0));
            System.out.println("Робот успешно создан!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании наземного робота с винтом сверху: " + e.getMessage());
        }

        try {
            Drone brokenDrone = new Drone("BrokenDrone", wheels, vision, fuelCell, lte, medicalKB, new Location(0, 0));
            System.out.println("Робот успешно создан!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании дрона на колесиках: " + e.getMessage());
        }

        try {
            GroundRobot goodRobot = new GroundRobot("goodRobot", wheels, vision, fuelCell, lte, medicalKB, new Location(0, 0));
            System.out.println("Робот успешно создан!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании дрона на колесиках: " + e.getMessage());
        }

    }


}