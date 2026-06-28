package factories;

import core.ICommunication;

public interface CommunicationFabric{
    public abstract ICommunication create();
}
