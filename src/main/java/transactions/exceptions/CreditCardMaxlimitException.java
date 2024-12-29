package transactions.exceptions;

public class CreditCardMaxlimitException extends RuntimeException {
    public CreditCardMaxlimitException(long amount){
        super(String.format("Credit card transaction cannot exceed %d amount", amount));
    }
}
