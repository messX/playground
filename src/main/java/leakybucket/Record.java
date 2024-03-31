package leakybucket;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Getter
@Setter
@Slf4j
public class Record {
    long fistInsertionTime;
    Integer count;

    public Record(){
        this.reset();
    }
    public void reset(){
        log.debug("Resetting the record");
        this.fistInsertionTime = Instant.now().getEpochSecond();
        this.count = 0;
    }

    public void increaseCount(){
        this.count++;
    }
}
