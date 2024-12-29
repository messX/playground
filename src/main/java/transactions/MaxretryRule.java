package transactions;

public class MaxretryRule implements IRule{

    public static final int MAX_RETRY_LIMIT = 3;

    @Override
    public boolean apply(Transaction transaction) {
        return transaction.getRetryCount() <= MAX_RETRY_LIMIT;
    }
}
