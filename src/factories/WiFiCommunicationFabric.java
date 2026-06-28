package factories;

import components.communication.WiFiCommunication;
import core.ICommunication;

public class WiFiCommunicationFabric implements CommunicationFabric {
    @Override
    public ICommunication create() {
        return new WiFiCommunication();
    }
}
