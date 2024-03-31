package testqueue;

public interface ITopic {
    void postMsg(String msg);
    String getMsg();
    void notifySubscribers();
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    //boolean popMsg();
}
