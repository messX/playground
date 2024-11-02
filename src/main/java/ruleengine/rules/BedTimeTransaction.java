package ruleengine.rules;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ruleengine.IRule;
import ruleengine.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class BedTimeTransaction implements IRule {
    private static final String BED_START_TIME = "00:00:00";
    private static final String BED_END_TIME = "04:00:00";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_PATTERN_COMPARISON = "HH:mm:ss";
    private DateTimeFormatter formatter;
    private DateTimeFormatter localFormatter;

    public BedTimeTransaction(){
        this.formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        this.localFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_COMPARISON);
    }

    @Override
    public boolean execute(Transaction t) throws ParseException {
        log.debug("Executing bed time rule");
        LocalTime transactionTs = LocalTime.parse(t.getTimestamp(), this.formatter);
        LocalTime localStartTs = LocalTime.parse(BED_START_TIME, this.localFormatter);
        LocalTime localEndTs = LocalTime.parse(BED_END_TIME, this.localFormatter);
        if(transactionTs.isAfter(localStartTs) && transactionTs.isBefore(localEndTs)){
            log.debug(String.format("BED time rule exception current time %s is between bedtime start: %s end: %s", t.getTimestamp(), BED_START_TIME, BED_END_TIME));
            return false;
        }
        return true;
    }
}
