public class LTECommunication extends ICommunication {
    LTECommunication() {connected = false;}
    @Override public void connect() { connected = true; System.out.println("LTE: соединение через сотовую сеть"); }
    @Override public void disconnect() { connected = false; System.out.println("LTE: соединение разорвано"); }
    @Override public void sendData(String data, String recipientId) {
        System.out.println("LTE: отправка данных получателю " + recipientId + ": " + data);
    }
    @Override public String receiveCommand(String command) {
        System.out.println("LTE: получение команды");
        return command;
    }
}
