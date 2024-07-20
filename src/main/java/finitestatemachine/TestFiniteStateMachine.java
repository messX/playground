package finitestatemachine;

public class TestFiniteStateMachine {
    public static void main(String[] args) {
        // we can also add order for more action
        OrderStateMachine orderStateMachine = new OrderStateMachine();
        orderStateMachine.handleEvent(OrderEvent.PROCESS_ORDER);
        orderStateMachine.handleEvent(OrderEvent.SHIP_ORDER);
        orderStateMachine.handleEvent(OrderEvent.DELIVER_ORDER);
        orderStateMachine.handleEvent(OrderEvent.CANCEL_ORDER);
        OrderStateMachine newOrderStateMachine = new OrderStateMachine();
        newOrderStateMachine.handleEvent(OrderEvent.CANCEL_ORDER);
    }
}
