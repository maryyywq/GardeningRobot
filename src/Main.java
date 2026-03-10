import java.util.*;

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

        //Создаем источники питания
        IPowerSource battery = new Battery();
        IPowerSource fuelCell = new FuelCell();

        //Создаем инструменты
        ITool wateringTool = new WateringTool();
        ITool fertilizingTool = new FertilizingTool();
        ITool medicalTool = new MedicalTool();

        //Создаём фабрику сегментов (для Flyweight)
        MapSegmentFactory segmentFactory = new MapSegmentFactory();

        System.out.println("1. Демонстрация паттерна Bridge");
        System.out.println("1.1. Демонстрация менеджера энергопотребления:");
        //Создаем роботов
        Robot ecoBattery = new Robot("EcoBattery", wheels, gps, new EcoPowerManager(battery), wifi, wateringKB, new Location(0, 0), segmentFactory);
        Robot ecoFuel = new Robot("EcoFuel", plane, gps, new EcoPowerManager(fuelCell), wifi, wateringKB, new Location(0, 1), segmentFactory);

        Robot maxFuel = new Robot("MaxFuel", legs, vision, new MaxPerformanceManager(fuelCell), lte, fertilizingKB, new Location(5, 5), segmentFactory);
        Robot maxBattery = new Robot("MaxBattery", legs, vision, new MaxPerformanceManager(battery), lte, fertilizingKB, new Location(5, 6), segmentFactory);

        Robot balanceBattery = new Robot("BalanceBattery", heli, gps, new BalancePowerManager(battery), lte, medicalKB, new Location(10, 10), segmentFactory);
        Robot balanceFuel  = new Robot("BalanceFuel", heli, gps, new BalancePowerManager(fuelCell), lte, medicalKB, new Location(10, 11), segmentFactory);

        System.out.println();

        //Устанавливаем инструменты
        ecoBattery.setTool(wateringTool);
        ecoFuel.setTool(wateringTool);
        maxFuel.setTool(fertilizingTool);
        maxBattery.setTool(fertilizingTool);
        balanceBattery.setTool(medicalTool);
        balanceFuel.setTool(medicalTool);

        System.out.println();

        //Экономный менеджер для батареи
        battery.setLevel(30.0);
        System.out.println("Начальный заряд: " + battery.getLevel() + "% (высокий)");
        ecoBattery.startTask();
        System.out.println("Заряд после задачи: " + battery.getLevel() + "%\n");

        battery.setLevel(24.0);
        System.out.println("Начальный заряд: " + battery.getLevel() + "% (низкий)");
        ecoBattery.startTask();
        System.out.println("Заряд после (робот зарядился): " + battery.getLevel() + "%\n");

        //Экономный менеджер для топлива
        fuelCell.setLevel(30.0);
        System.out.println("Начальный уровень топлива: " + fuelCell.getLevel() + "% (высокий)");
        ecoFuel.startTask(); // выполняется, уровень становится 25%
        System.out.println("Уровень после задачи: " + fuelCell.getLevel() + "%\n");

        fuelCell.setLevel(24.0);
        System.out.println("Начальный уровень топлива: " + fuelCell.getLevel() + "% (низкий)");
        ecoFuel.startTask();
        System.out.println("Уровень после (заправился): " + fuelCell.getLevel() + "%\n");

        //Менеджер с максимальной производительностью для топлива
        fuelCell.setLevel(30.0);
        System.out.println("Начальный уровень топлива: " + fuelCell.getLevel() + "% (высокий)");
        maxFuel.startTask();
        System.out.println("Уровень после задачи: " + fuelCell.getLevel() + "%\n");

        fuelCell.setLevel(7.0);
        System.out.println("Начальный уровень топлива: " + fuelCell.getLevel() + "% (низкий)");
        maxFuel.startTask();
        System.out.println("Уровень после (переключился на резерв): " + fuelCell.getLevel() + "%\n");

        //Менеджер с максимальной производительностью для батареи
        battery.setLevel(30.0);
        System.out.println("Начальный заряд: " + battery.getLevel() + "% (высокий)");
        maxBattery.startTask();
        System.out.println("Заряд после задачи: " + battery.getLevel() + "%\n");

        battery.setLevel(7.0);
        System.out.println("Начальный заряд: " + battery.getLevel() + "% (низкий, без резерва)");
        maxBattery.startTask();
        System.out.println("Заряд после (задача не выполнена): " + battery.getLevel() + "%\n");


        //Сбалансированный менедженр для батареи
        battery.setLevel(20.0);
        System.out.println("Начальный заряд: " + battery.getLevel() + "% (высокий)");
        balanceBattery.startTask();
        System.out.println("Заряд после задачи: " + battery.getLevel() + "%\n");

        battery.setLevel(11.0);
        System.out.println("Начальный заряд: " + battery.getLevel() + "% (низкий, без резерва)");
        balanceBattery.startTask();
        System.out.println("Заряд после (зарядился): " + battery.getLevel() + "%\n");

        //Сбалансированный менедженр для топлива
        fuelCell.setLevel(20.0);
        System.out.println("Начальный уровень топлива: " + fuelCell.getLevel() + "% (высокий)");
        balanceFuel.startTask();
        System.out.println("Уровень после задачи: " + fuelCell.getLevel() + "%\n");

        fuelCell.setLevel(11.0);
        System.out.println("Начальный уровень топлива: " + fuelCell.getLevel() + "% (низкий, есть резерв)");
        balanceFuel.startTask();
        System.out.println("Уровень после (переключился на резерв): " + fuelCell.getLevel() + "%\n");

        Location loc = new Location(10, 10);

        System.out.println("1.2. Демонстрация систем передвижения:");
        ecoBattery.moveTo(loc); //Колеса
        System.out.println();
        ecoFuel.moveTo(loc); //Самолет
        System.out.println();
        maxFuel.moveTo(loc); //Ножки
        System.out.println();
        balanceBattery.moveTo(loc); //Вертолет
    }
}