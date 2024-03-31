package testqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Topic implements ITopic{
    //Queue<String> queue;
    ArrayList<ISubscriber> subscribers;
    String message;
    private boolean changed;
    private final Object MUTEX= new Object();

    Topic(){
        this.subscribers = new ArrayList<ISubscriber>();
        //this.queue = new LinkedList<>();
    }

    @Override
    public void postMsg(String msg) {
        this.message = msg;
        this.changed = true;
        this.notifySubscribers();
    }

    @Override
    public String getMsg() {
        //return this.queue.peek();
        return this.message;
    }

    @Override
    public void notifySubscribers() {
        List<ISubscriber> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.subscribers);
            this.changed=false;
        }
        for (ISubscriber obj : observersLocal) {
            obj.update();
        }

    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        synchronized (MUTEX){
            if (!subscribers.contains(subscriber))
                this.subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        synchronized (MUTEX) {
            this.subscribers.remove(subscriber);
        }
    }
}
