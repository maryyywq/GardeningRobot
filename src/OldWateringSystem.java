class OldWateringSystem {
    private double waterAmount;   //текущий объем воды в литрах
    private double capacity;       //максимальная вместимость
    private double flowRate;       //расход воды за один полив (литров)

    public OldWateringSystem(double capacity, double flowRate) {
        this.capacity = capacity;
        this.flowRate = flowRate;
        this.waterAmount = capacity; //изначально полный
    }

    public OldWateringSystem(OldWateringSystem other) {
        this.waterAmount = other.waterAmount;
        this.capacity = other.capacity;
        this.flowRate = other.flowRate;
    }

    public void turnOn() {
        if (waterAmount >= flowRate) {
            System.out.println("Старая поливалка: включена, расходует " + flowRate + " л воды");
            waterAmount -= flowRate;
        } else {
            System.out.println("Старая поливалка: недостаточно воды! Осталось " + waterAmount + " л");
        }
    }

    public void turnOff() {
        System.out.println("Старая поливалка: выключена");
    }

    public void refill() {
        waterAmount = capacity;
        System.out.println("Старая поливалка: резервуар наполнен до " + capacity + " л");
    }

    public double getWaterAmount() {
        return waterAmount;
    }
}
