package testqueue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subscriber implements ISubscriber{

    String condition;
    ITopic topic;
    String name;

    Subscriber(String reg, ITopic topic, String name){
        this.condition = reg;
        this.topic = topic;
        this.name = name;
    }

    @Override
    public boolean checkCondition(String str) {
        Pattern pattern = Pattern.compile(this.condition);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    @Override
    public void update() {
        if(checkCondition(this.topic.getMsg())){
            System.out.println(String.format("Recieved msg in subscriber %s: %s", this.name, this.topic.getMsg()));
        }
    }
}
