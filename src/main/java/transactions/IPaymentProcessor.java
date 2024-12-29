package transactions;

public interface IPaymentProcessor {
    void process(long amount);
    void validate(long amount);
}
