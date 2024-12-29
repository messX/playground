package transactions.exceptions;

public class LessthanzerotransactionException extends RuntimeException {
    public LessthanzerotransactionException(){
        super("Transaction Amount cannot be 0 or less");
    }
}
