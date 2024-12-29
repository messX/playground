package transactions;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transactions.exceptions.CreditCardMaxlimitException;
import transactions.exceptions.LessthanzerotransactionException;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class TransactionManager {
    IPaymentProcessor creditCardProcessor;
    final List<IRule> rules = List.of(
            new MaxretryRule(),
            new MaxAmountRule()
    );
    Transaction transaction;

    public TransactionManager(Transaction transaction){
        this.transaction = transaction;
        this.creditCardProcessor = new CreditcardPaymentProcessor();
    }

    private boolean checkIdempotency(){
        List<Transaction> transactions = TransactionRepository.transactionList;
        for(Transaction transaction1: transactions){
            if(transaction.idempotencyKey.equals(transaction1.idempotencyKey)){
                log.info("Transaction already processed");
                return false;
            }
        }
        return true;
    }
    
    public void process() throws RuntimeException {
        log.info("Checking idempotency");
        if(!checkIdempotency()){
            throw new RuntimeException(String.format("Already processed transaction Idempotency: %s",
                    transaction.getIdempotencyKey()));
        }
        log.info("Applying transaction rules");
        for (IRule rule : rules) {
            rule.apply(this.transaction);
        }
        log.info("processing transaction post check");
        while (true){
            try{
                creditCardProcessor.process(transaction.amount);
                log.info(String.format("Processed transaction %s", this.transaction.getId()));
                break;
            }
            catch (LessthanzerotransactionException | CreditCardMaxlimitException e){
                log.debug(String.format("Credit card processing exception %s", e.toString()));
                throw e;
            }
            catch (RuntimeException exp){
                log.info("Credit card processing issue, retying");
                this.transaction.retryCount++;
                retry();
            }
        }

    }

    private void retry() {
        try {
            // exponential backoff for retry
            Thread.sleep((long) (Math.pow(2, this.transaction.retryCount) * 1000));
            log.info(String.format("Processing transaction %s post backoff, retry count ",
                    this.transaction.getId(), this.transaction.getRetryCount()));
            process();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
