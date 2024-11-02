package ruleengine.rules;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ruleengine.IRule;
import ruleengine.Transaction;

@Slf4j
@NoArgsConstructor
public class HighValueTransaction implements IRule {

    private static final float HIGH_VALUE_AMOUNT = 5000.0F;

    @Override
    public boolean execute(Transaction t) {
        log.debug("Executing high value rule");
        if(t.getAmount() >= HIGH_VALUE_AMOUNT){
            log.debug(String.format("High value transaction rule exception, current amount %f, allowed amount %f", t.getAmount(), HIGH_VALUE_AMOUNT));
            return false;
        }
        else{
            return true;
        }
    }
}
