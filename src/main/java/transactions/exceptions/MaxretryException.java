package transactions.exceptions;

public class MaxretryException extends RuntimeException {
    public MaxretryException(int retryLimit){
        super(String.format("MAX retry limit reached: %d", retryLimit));
    }
}
