import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Создание базовых компонентов
        IMovementSystem wheels = new WheeledMovement();
        IMovementSystem legs = new LeggedMovement();
        IMovementSystem heli = new HelicopterMovement();
        IMovementSystem plane = new PlaneMovement();
        INavigation gps = new GPSNavigation();
        INavigation vision = new VisionNavigation();
        IPowerSource battery = new Battery();
        IPowerSource fuelCell = new FuelCell();
        ICommunication wifi = new WiFiCommunication();
        ICommunication lte = new LTECommunication();

        //Создание баз знаний
        WateringKnowledgeBase wateringKB = new WateringKnowledgeBase();
        wateringKB.addEntry("томат", new WateringEntry(2.5, "дождевание"));
        wateringKB.addEntry("огурец", new WateringEntry(3.0, "точечный"));

        FertilizingKnowledgeBase fertilizingKB = new FertilizingKnowledgeBase();
        fertilizingKB.addEntry("томат", new FertilizingEntry("NPK 10-10-10", 50));

        MedicalKnowledgeBase medicalKB = new MedicalKnowledgeBase();
        medicalKB.addEntry("фитофтора", new MedicalEntry("бордоская смесь", 100));

        //Создание роботов
        Robot robot1 = new Robot("R2D2", wheels, gps, battery, wifi, wateringKB, new Location(0, 0));
        Robot robot2 = new Robot("C3PO", legs, vision, fuelCell, lte, fertilizingKB, new Location(5, 5));
        Robot robot3 = new Robot("MediDrone", heli, gps, battery, lte, medicalKB, new Location(10, 10));
        //Для четвёртого робота используем пустую базу знаний полива
        Robot robot4 = new Robot("MowDrone", plane, vision, fuelCell, wifi, new WateringKnowledgeBase(), new Location(20, 20));

        System.out.println("1. Создание роботов:");
        System.out.println(robot1);
        System.out.println(robot2);
        System.out.println(robot3);
        System.out.println(robot4 + "\n");


        System.out.println("2. Демонстрация паттерна адаптер:");
        OldWateringSystem oldWatering = new OldWateringSystem(20.0, 2.0);
        oldWatering.turnOn();
        oldWatering.turnOff();
        ITool adaptedTool = new OldWateringAdapter(oldWatering);
        robot1.setTool(adaptedTool);
        robot1.receiveCommand("WATER");
        System.out.println();


        System.out.println("3. Демонстрация паттерна декоратор:");

        ITool fertilizing = new FertilizingTool(); // базовый инструмент для удобрения

        // 1.Только музыка
        System.out.println("Вариант 1. Только музыка:");
        ITool musicOnly = new SoundToolDecorator(fertilizing, "There's a zombie on your lawn");
        robot2.setTool(musicOnly);
        robot2.receiveCommand("FERTILIZE");
        System.out.println();

        //2.Все три декоратора в порядке: музыка, подсветка, голос
        System.out.println("Вариант 2. Музыка, подсветка, голос:");
        ITool allInOrder1 = new VoiceToolDecorator(
                new LightToolDecorator(
                        new SoundToolDecorator(fertilizing, "We are the champions, my friend..."),
                        "розовый", 2),
                "Мария");
        robot2.setTool(allInOrder1);
        robot2.receiveCommand("FERTILIZE");
        System.out.println();

        //3. Все три декоратора в другом порядке
        System.out.println("Вариант 3. Голос + подсветка + музыка:");
        ITool allInOrder2 = new SoundToolDecorator(
                new LightToolDecorator(
                        new VoiceToolDecorator(fertilizing, "Господин"),
                        "зелёный", 15),
                "Never give it up, keep it up a superstar!");
        robot2.setTool(allInOrder2);
        robot2.receiveCommand("FERTILIZE");
        System.out.println();

        System.out.println("4. Демонстрация паттерна композит:");
        RobotGroup groupA = new RobotGroup("Грядка-1");
        groupA.addRobot(robot1);
        groupA.addRobot(robot2);

        RobotGroup groupB = new RobotGroup("Теплица");
        groupB.addRobot(robot3);
        groupB.addRobot(robot4);
        groupB.addRobot(groupA); //вложенная группа

        System.out.println("Вывод состава групп:");
        System.out.println(groupB);
        System.out.println(groupA);
        System.out.println();
        groupB.receiveCommand("START");
        groupB.startTask();
        System.out.println();
        System.out.println("Статус группы B: " + groupB.getStatus().getDescription());
        groupB.stopTask();
        System.out.println();


        System.out.println("5. Демонстрация паттерна итератор:");

        //Итератор по компонентам робота
        System.out.println("Компоненты робота R2D2:");
        for (Object comp : robot1) {
            System.out.println(comp);
        }
        System.out.println();

        //Итератор по группе
        System.out.println("Роботы в группе 'Грядка-1':");
        for (IRobot r : groupA) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Роботы в группе 'Теплица':");
        for (IRobot r : groupB) {
            System.out.println(r);
        }
    }
}