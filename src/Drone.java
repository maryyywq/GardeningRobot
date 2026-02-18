public class Drone extends Robot {
    public Drone(String id, IMovementSystem ms, INavigation nav, IPowerSource ps,
                 ICommunication comm, IKnowledgeBase kb, Location startLoc) {
        super(id, ms, nav, ps, comm, kb, startLoc);
    }
}
