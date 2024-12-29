package transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    long amount;
    Date timestamp;
    String id;
    TransactionStatus status;

    String idempotencyKey;

    int retryCount;

    public void initTransaction(long amount){
        this.id = UUID.randomUUID().toString();
        this.idempotencyKey = this.idempotencyKey != null ? this.idempotencyKey : UUID.randomUUID().toString();
        this.timestamp = new Date();
        this.amount = amount;
        this.status = TransactionStatus.INPROGRESS;
        this.retryCount = 0;
    }

    @Override
    public String toString() {
        return String.format("Transaction id: %s, started at %s, amount %d, status %s",
                this.id, this.timestamp, this.amount, this.status.toString());
    }
}
