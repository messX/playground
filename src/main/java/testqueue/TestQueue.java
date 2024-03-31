package testqueue;

public class TestQueue {
    public static void main(String[] args) {
        Topic topic = new Topic();
        Subscriber subscriber1 = new Subscriber("test", topic, "sub1");
        Subscriber subscriber2 = new Subscriber("\\d+", topic, "sub2");
        topic.addSubscriber(subscriber1);
        topic.addSubscriber(subscriber2);
        Publisher publisher = new Publisher(topic, "pub1");
        publisher.publish("I am test");
        publisher.publish("this is 123");
    }
}
