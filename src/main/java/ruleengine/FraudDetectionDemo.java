package ruleengine;

import lombok.extern.slf4j.Slf4j;
import ruleengine.rules.BedTimeTransaction;
import ruleengine.rules.HighValueTransaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FraudDetectionDemo {
    public static void main(String[] args) throws ParseException {
        Transaction t1 = new Transaction(5000.0F, "2024-12-01 12:01:01", 1, "1");
        Transaction t2 = new Transaction(15000.0F, "2024-12-10 02:01:01", 1, "2");
        Transaction t3 = new Transaction(2000.0F, "2024-12-01 13:01:01", 1, "3");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(t1);
        transactions.add(t2);
        transactions.add(t3);
        List<IRule> rules = new ArrayList<>();
        rules.add(new BedTimeTransaction());
        rules.add(new HighValueTransaction());
        FraudDetectionRuleEngine fraudDetectionRuleEngine = new FraudDetectionRuleEngine(rules);
        log.debug("Running rule engine");
        for(Transaction t: transactions){
            if(fraudDetectionRuleEngine.isFraud(t)){
                log.debug(String.format("Transaction %s might be fraud", t.getId()));
            }
        }
    }
}
