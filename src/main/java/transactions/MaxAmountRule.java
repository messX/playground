package transactions;


public class MaxAmountRule implements IRule{

    public final long MAX_PAYMENT_AMOUNT = (long) 100000000.0;

    @Override
    public boolean apply(Transaction transaction) {
        return transaction.getAmount() <= MAX_PAYMENT_AMOUNT;
    }
}
