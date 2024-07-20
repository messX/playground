package finitestatemachine;

public class Transition {
    private final OrderState nextState;
    private final OrderAction action;

    public Transition(OrderState nextState, OrderAction action){
        this.action = action;
        this.nextState = nextState;
    }
    public OrderState getNextState() {
        return nextState;
    }

    public OrderAction getAction() {
        return action;
    }
}
