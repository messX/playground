package finitestatemachine;

import java.util.HashMap;
import java.util.Map;

public class OrderStateMachine {
    private OrderState currentState;
    private final Map<OrderState, Map<OrderEvent, Transition>> orderStateMap = new HashMap<>();

    public OrderStateMachine(){
        initialiseTransition();
        currentState = OrderState.NEW;
    }

    /**
     * We can use this method to dynamically
     * initialise transition from a file
     * We can also pass order object and action object
     * in case of complex logic
     */
    private void initialiseTransition() {
        // NEW --> Process order
        addTransition(OrderState.NEW, OrderEvent.PROCESS_ORDER, OrderState.PROCESSING, () -> {
            System.out.println("Processing Order action from New");
        });
        // PROCESS_ORDER --> SHIPPED
        addTransition(OrderState.PROCESSING, OrderEvent.SHIP_ORDER, OrderState.SHIPPED, () -> {
            System.out.println("Ship Order action from processing");
        });
        // SHIPPED --> DELIVERED
        addTransition(OrderState.SHIPPED, OrderEvent.DELIVER_ORDER, OrderState.DELIVERED, () -> {
            System.out.println("DEliver Order action from Shipped");
        });
        // PROCESS_ORDER --> CANCELLED
        addTransition(OrderState.PROCESSING, OrderEvent.CANCEL_ORDER, OrderState.CANCELLED, () -> {
            System.out.println("Cancel action from processing");
        });
        // NEW --> CANCELLED
        addTransition(OrderState.NEW, OrderEvent.CANCEL_ORDER, OrderState.CANCELLED, () -> {
            System.out.println("Cancel action from new");
        });
    }

    private void addTransition(OrderState fromState, OrderEvent event, OrderState toState, OrderAction action) {
        Transition transition = new Transition(toState, action);
        if(!orderStateMap.containsKey(fromState))
        {
            Map<OrderEvent, Transition> transitionMap = new HashMap<>();
            orderStateMap.put(fromState, transitionMap);
        }
        orderStateMap.get(fromState).put(event, transition);
    }

    public void handleEvent(OrderEvent event){
        Map<OrderEvent, Transition> transitionMap = orderStateMap.get(this.currentState);
        if(transitionMap != null){
            Transition transition = transitionMap.get(event);
            System.out.println("Execute action");
            transition.getAction().execute();
            System.out.println(String.format("Transitioned to %s, from %s", transition.getNextState(), this.currentState));
            this.currentState = transition.getNextState();
            return;
        }
        System.out.println(String.format("Invalid state event %s current state is %s",
                event, this.currentState));
    }

}
