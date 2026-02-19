public class WiFiCommunication implements ICommunication {
    private boolean connected = false;
    @Override public void connect() { connected = true; System.out.println("WiFi: соединение установлено"); }
    @Override public void disconnect() { connected = false; System.out.println("WiFi: соединение разорвано"); }
    @Override public void sendData(String data, String recipientId) {
        System.out.println("WiFi: отправка данных получателю " + recipientId + ": " + data);
    }
    @Override public String receiveCommand(String command) {
        System.out.println("WiFi: получение команды");
        return command;
    }
}
