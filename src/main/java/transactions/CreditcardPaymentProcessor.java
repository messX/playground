package transactions;

import lombok.extern.slf4j.Slf4j;
import transactions.exceptions.CreditCardMaxlimitException;
import transactions.exceptions.LessthanzerotransactionException;

@Slf4j
public class CreditcardPaymentProcessor implements IPaymentProcessor{

    public static final long MAX_LIMIT = (long) 1000000.0;

    @Override
    public void process(long amount) {
        this.validate(amount);
        log.info("Transaction processed via credit card without exception");
    }

    @Override
    public void validate(long amount) {
        if(amount <= 0){
            throw new LessthanzerotransactionException();
        }
        else if(amount > MAX_LIMIT){
            throw new CreditCardMaxlimitException(MAX_LIMIT);
        }
    }
}
