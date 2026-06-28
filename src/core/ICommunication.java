package core;

//Абстрактный класс связи
public abstract class ICommunication {
    protected boolean connected;
    protected abstract void connect(); //Установить соединение
    protected abstract void disconnect(); //Разорвать соединение
    protected abstract void sendData(String data, String recipientId);  //Отправить данные получателю
    protected abstract String receiveCommand(String command); //Принять команду
    @Override public abstract ICommunication clone();
}
