public class GroundRobot extends Robot {
    public GroundRobot(String id, IMovementSystem ms, INavigation nav, IPowerSource ps,
                       ICommunication comm, IKnowledgeBase kb, Location startLoc) {
        super(id, ms, nav, ps, comm, kb, startLoc);

        if (ms.getMovementType() != MovementType.GROUND) {
            throw new IllegalArgumentException("Наземный робот не может использовать летающий тип передвижения!");
        }

    }
}
