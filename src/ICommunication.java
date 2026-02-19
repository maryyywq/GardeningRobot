//Интерфейс связи
public interface ICommunication {
    void connect();
    void disconnect();
    void sendData(String data, String recipientId);
    String receiveCommand(String command);
}
