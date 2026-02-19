public class Drone extends Robot {
    public Drone(String id, IMovementSystem ms, INavigation nav, IPowerSource ps,
                 ICommunication comm, IKnowledgeBase kb, Location startLoc) {
        super(id, ms, nav, ps, comm, kb, startLoc);
        if (ms.getMovementType() != MovementType.AIR) {
            throw new IllegalArgumentException("Дрон не может использовать наземный тип передвижения!");
        }

    }
}
