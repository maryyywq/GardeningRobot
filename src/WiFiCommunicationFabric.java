public class WiFiCommunicationFabric implements CommunicationFabric {
    @Override
    public ICommunication create() {
        return new WiFiCommunication();
    }
}
