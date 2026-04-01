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
    }

    }

