public class LTECommunicationFabric implements CommunicationFabric {
    @Override
    public ICommunication create() {
        return new LTECommunication();
    }

}
