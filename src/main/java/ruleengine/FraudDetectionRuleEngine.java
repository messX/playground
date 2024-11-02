package ruleengine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudDetectionRuleEngine {
    private List<IRule> rules;

    public boolean isFraud(Transaction t) throws ParseException {
        for(IRule rule: rules){
            if(!rule.execute(t)){
                log.debug(String.format("Fraud detected for transaction %s", t.getId()));
                return true;
            }
        }
        return false;
    }
}
