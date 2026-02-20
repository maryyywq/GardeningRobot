//Интерфейс связи
public interface ICommunication {
    void connect(); //Установить соединение
    void disconnect(); //Разорвать соединение
    void sendData(String data, String recipientId);  //Отправить данные получателю
    String receiveCommand(String command); //Принять команду
}
