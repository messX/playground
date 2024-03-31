package testqueue;

public interface ISubscriber {
     boolean checkCondition(String str);

    void update();
}
