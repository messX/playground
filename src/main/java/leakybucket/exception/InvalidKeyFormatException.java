package leakybucket.exception;

public class InvalidKeyFormatException extends RuntimeException{
    public InvalidKeyFormatException(String msg){
        super(msg);
    }
}
