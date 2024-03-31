package leakybucket.exception;

public class BucketLeakedException extends RuntimeException{
    public BucketLeakedException(String msg){
        super(msg);
    }
}
