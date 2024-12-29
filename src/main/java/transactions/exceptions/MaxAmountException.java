package transactions.exceptions;

public class MaxAmountException extends RuntimeException {
    public MaxAmountException(float amount){
        super(String.format("MAX amount limit reached: %d", amount));
    }
}
