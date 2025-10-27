package error_handler.observer;


public interface Publisher {

    void notifyAll(Object errorMessage);
    void addSubscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub);
}
