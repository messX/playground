package ruleengine;

import java.text.ParseException;

public interface IRule {
    boolean execute(Transaction t) throws ParseException;
}
