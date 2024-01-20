package testqueue;

public class Publisher implements IPublisher{

    ITopic topic;
    String name;

    Publisher(ITopic topic, String name){
        this.topic = topic;
        this.name = name;
    }
    @Override
    public void publish(String msg) {
        System.out.println("Publishing in publisher: "+ this.name);
        this.topic.postMsg(msg);
    }
}
