import java.util.List;
import java.util.Map;

class GardenFacade {
    private CentralController controller;
    private MapSegmentFactory mapFactory;

    public GardenFacade(CentralController controller, MapSegmentFactory mapFactory) {
        this.controller = controller;
        this.mapFactory = mapFactory;
    }

    //Вырастить помидоры на указанной грядке
    public void growTomatoes(Location bedLocation) {
        System.out.println("Запуск процесса выращивания помидоров на грядке " + bedLocation);

        //1. Обновляем данные о сегменте карты
        MapSegment segment = mapFactory.getMapSegment(bedLocation);
        segment.setSoilType("чернозём");
        segment.setPlant("томаты");
        segment.setWatteringLevel(2.5);
        System.out.println("Шаг 1: Данные о грядке обновлены");

        //2. Посадка
        IRobot plantingRobot = controller.findRobotWithTool(ToolType.PLANTING);
        if (plantingRobot != null) {
            System.out.println("Шаг 2: Посадка...");
            Task plantingTask = new Task("PLANT", Map.of("plant", "томаты", "location", bedLocation));
            controller.assignTask(plantingRobot.getRobotId(), plantingTask);
        } else {
            System.out.println("Нет робота-посадчика!");
            return;
        }

        //3. Полив
        IRobot wateringRobot = controller.findRobotWithTool(ToolType.WATERING);
        if (wateringRobot != null) {
            System.out.println("Шаг 3: Полив...");
            Task waterTask = new Task("WATER", Map.of("plant", "томаты", "volume", 2.5));
            controller.assignTask(wateringRobot.getRobotId(), waterTask);

        }else {
            System.out.println("Нет робота для полива!");
            return;
        }

        //4. Удобрение
        IRobot fertilizingRobot = controller.findRobotWithTool(ToolType.FERTILIZING);
        if (fertilizingRobot != null) {
            System.out.println("Шаг 4: Удобрение...");
            Task fertilizeTask = new Task("FERTILIZE", Map.of("plant", "томаты", "dosage", 50));
            controller.assignTask(fertilizingRobot.getRobotId(), fertilizeTask);
        } else {
            System.out.println("Нет робота для удобрения!");
            return;
        }

        //5. Обработка от болезней
        IRobot medicalRobot = controller.findRobotWithTool(ToolType.MEDICAL);
        if (medicalRobot != null) {
            System.out.println("Шаг 5: Обработка от болезней...");
            Task medicalTask = new Task("TREAT", Map.of("disease", "фитофтора", "medicine", "бордоская смесь"));
            controller.assignTask(medicalRobot.getRobotId(), medicalTask);
        } else {
            System.out.println("Нет робота-медика!");
            return;
        }

        System.out.println("Процесс выращивания помидоров запущен");
    }

    //Полить все грядки (все сегменты карты, где есть растения)
    public void waterAllBeds() {
        System.out.println("Запуск полива всех грядок");
        List<IRobot> waterers = controller.findAllRobotsWithTool(ToolType.WATERING);
        if (waterers.isEmpty()) {
            System.out.println("Нет роботов-поливальщиков");
            return;
        }
        for (IRobot robot : waterers) {
            Task waterTask = new Task("WATER", Map.of("volume", 5.0));
            controller.assignTask(robot.getRobotId(), waterTask);
        }
        System.out.println("Команды полива отправлены " + waterers.size() + " роботам");
    }

    //Собрать урожай со всех грядок
    public void harvestAllBeds() {
        System.out.println("Запуск сбора урожая");
        List<IRobot> harvesters = controller.findAllRobotsWithTool(ToolType.HARVESTING);
        if (harvesters.isEmpty()) {
            System.out.println("Нет роботов-сборщиков");
            return;
        }
        for (IRobot robot : harvesters) {
            Task harvestTask = new Task("HARVEST", Map.of("crop", "томаты"));
            controller.assignTask(robot.getRobotId(), harvestTask);
        }
        System.out.println("Команды сбора отправлены " + harvesters.size() + " роботам");
    }

    //Удобрить все грядки
    public void fertilizeAllBeds() {
        System.out.println("Запуск удобрения всех грядок");
        List<IRobot> fertilizers = controller.findAllRobotsWithTool(ToolType.FERTILIZING);
        if (fertilizers.isEmpty()) {
            System.out.println("Нет роботов-удобрителей");
            return;
        }
        for (IRobot robot : fertilizers) {
            Task fertilizeTask = new Task("FERTILIZE", Map.of("type", "комплексное", "dosage", 30));
            controller.assignTask(robot.getRobotId(), fertilizeTask);
        }
        System.out.println("Команды удобрения отправлены " + fertilizers.size() + " роботам");
    }

}