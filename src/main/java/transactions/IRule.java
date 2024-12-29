package transactions;

public interface IRule {
    boolean apply(Transaction transaction);
}
