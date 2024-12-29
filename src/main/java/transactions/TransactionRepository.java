package transactions;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class TransactionRepository {
    static List<Transaction> transactionList = new ArrayList<>();
}
