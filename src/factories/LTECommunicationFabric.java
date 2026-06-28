package factories;

import components.communication.LTECommunication;
import core.ICommunication;

public class LTECommunicationFabric implements CommunicationFabric {
    @Override
    public ICommunication create() {
        return new LTECommunication();
    }

}
