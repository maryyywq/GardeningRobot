//Абстрактный класс связи
abstract class ICommunication {
    protected boolean connected;
    abstract void connect(); //Установить соединение
    abstract void disconnect(); //Разорвать соединение
    abstract void sendData(String data, String recipientId);  //Отправить данные получателю
    abstract String receiveCommand(String command); //Принять команду
}
