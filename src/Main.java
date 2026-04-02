import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("1. Тестирование паттерна Builder:\n");

        //1. Создаём билдер
        MapSegmentBuilder builder = new StandartMapSegmentBuilder();

        //2. Директор для помидорной грядки
        MapSegmentDirector tomatoDirector = new TomatoGardenDirector();
        tomatoDirector.construct(builder);
        MapSegment tomatoBed = builder.build();
        System.out.println("Помидорная грядка:\n" + tomatoBed + "\n");

        //3. Директор для газона (новый билдер)
        MapSegmentBuilder builder2 = new StandartMapSegmentBuilder();
        MapSegmentDirector lawnDirector = new LawnDirector();
        lawnDirector.construct(builder2);
        MapSegment lawn = builder2.build();
        System.out.println("Газон:\n" + lawn + "\n");

        //4. Директор для морковной грядки
        MapSegmentBuilder builder3 = new StandartMapSegmentBuilder();
        MapSegmentDirector carrotDirector = new CarrotSandGardenDirector();
        carrotDirector.construct(builder3);
        MapSegment carrotBed = builder3.build();
        System.out.println("Морковная грядка на песке:\n" + carrotBed + "\n");

        MapSegmentFactory segmentFactory = new MapSegmentFactory();

        //5. Регистрация своего сегмента через фабрику
        MapSegment customSegment = new StandartMapSegmentBuilder()
                .setSoil(SoilType.CLAY)
                .setPlant(PlantType.CUCUMBER)
                .setMoisture(2.0)
                .setWeeds(WeedLevel.MEDIUM)
                .addObject(GroundObjectType.STONE)
                .addObject(GroundObjectType.PUDDLE)
                .build();

        Location customLoc = new Location(5, 5);
        segmentFactory.registerSegment(customLoc, customSegment);
        MapSegment retrieved = segmentFactory.getMapSegment(customLoc);
        System.out.println("Зарегистрированный свой сегмент:\n" + retrieved);
        System.out.println();

        System.out.println("2. Тестирование паттерна State:\n");
        CentralController controller = CentralController.getInstance();

        //Создаём робота через фабрику (подходит для полива)
        RobotFactory factory = new WateringRobotFactory();
        Robot robot = factory.createRobot("Robot-001", new Location(0, 0), segmentFactory);

        //Добавляем инструмент в пул контроллера
        controller.addToolToPool(new WateringTool());

        //Регистрируем робота в контроллере (он получает доступ к пулу)
        controller.registerRobot(robot);
        System.out.println();

        System.out.println("2.1. Демонстрация состояния простоя: ");
        robot.act();
        System.out.println();
        System.out.println("2.2. Демонстрация состояния работы:");
        robot.startWorking();
        controller.assignTask("Robot-001", new Task("WATER", Map.of("volume", 2.5)));
        System.out.println();

        System.out.println("2.3. Демонстрация зарядки:");
        robot.startCharging();
        robot.act();
        System.out.println("Статус: " + robot.getStatus());
        System.out.println();

        System.out.println("2.4. Демонстрация состояния движения:");
        robot.startMoving(new Location(10, 20));
        robot.act();
        System.out.println("Статус: " + robot.getStatus());
        System.out.println();

        System.out.println("2.5. Демонстрация состояния ошибки:");
        robot.handleError();
        System.out.println("Статус: " + robot.getStatus());
        robot.act();
        System.out.println("Статус после восстановления: " + robot.getStatus());
        System.out.println();

        System.out.println("3. Тестирование паттерна Memento:\n");
        controller.clearRobots();
        controller.addToolToPool(new WateringTool());

        Robot r1 = factory.createRobot("Robot-001", new Location(0,0), segmentFactory);
        Robot r2 = factory.createRobot("Robot-002", new Location(1,1), segmentFactory);
        Robot r3 = factory.createRobot("Robot-003", new Location(2,2), segmentFactory);

        controller.registerRobot(r1);
        controller.registerRobot(r2);
        controller.registerRobot(r3);
        System.out.println();

        System.out.print("Состояние 1 (3 робота): ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println();
        controller.saveToHistory();
        System.out.println();

        controller.removeRobot("Robot-003");
        System.out.print("Состояние 2 (2 робота): ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println();
        controller.saveToHistory();
        System.out.println();

        controller.removeRobot("Robot-002");
        System.out.print("Состояние 3 (1 робот): ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println();
        controller.saveToHistory();
        System.out.println();

        controller.clearRobots();
        System.out.print("Состояние 4 (0 роботов): ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println();
        controller.saveToHistory();
        System.out.println();

        System.out.println("3.1. Откаты (undo):");
        controller.undo();
        System.out.print("После undo 1: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println();

        controller.undo();
        System.out.print("После undo 2: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); // ожидается 2

        controller.undo();
        System.out.print("После undo 3: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); // ожидается 3

        controller.undo();
        System.out.print("После undo 4: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); //останется 3
        System.out.println();

        System.out.println("3.2. Повторы (redo):");
        controller.redo();
        System.out.print("После redo 1: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); // ожидается 2

        controller.redo();
        System.out.print("После redo 2: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); // ожидается 1

        controller.redo();
        System.out.print("После redo 3: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); // ожидается 0

        controller.redo();
        System.out.print("После redo 4: ");
        for (IRobot r : controller.getAllRobots()) System.out.print(r.getRobotId() + " ");
        System.out.println(); // останется 0
        System.out.println();

        System.out.println("4. Демонстрация паттерна Observer: \n");
        //Очищаем контроллер от старых роботов
        FertilizingTool tool =  new FertilizingTool();
        controller.clearRobots();
        controller.addToolToPool(tool);

        RobotFactory ferFactory= new FertilizingRobotFactory();
        Robot ferRobot = ferFactory.createRobot("Wally", new Location(0, 0), segmentFactory);
        controller.registerRobot(ferRobot);
        ferRobot.startWorking();
        System.out.println();

        System.out.println("4.1. Выполнение задачи с инструментом:");
        controller.assignTask("Wally", new Task("FERTILIZE", Map.of("чернозем", 2.5)));
        System.out.println();

        System.out.println("4.2. Выполнение задачи без инструмента:");
        controller.removeToolFromPool(tool);
        controller.assignTask("Wally", new Task("FERTILIZE", Map.of("чернозем", 2.5)));
        System.out.println();

        System.out.println("4.3. Движение робота:");
        ferRobot.startMoving(new Location(10, 20));
        ferRobot.act();
        System.out.println();

        System.out.println("4.4. Зарядка робота:");
        ferRobot.startCharging();
        ferRobot.act();
    }

    }


