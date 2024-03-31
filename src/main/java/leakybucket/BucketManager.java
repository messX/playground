package leakybucket;

import leakybucket.exception.BucketLeakedException;
import leakybucket.exception.InvalidKeyFormatException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Bucket manager to manage the leaky bucket
 * Addition fixes:
 * - Clean the unused key if time is elapsed else it can lead to memory leak
 * - Make the key pattern dynamic
 */

@Slf4j
@Getter
@Setter
public class BucketManager implements IBucketManager{
    HashMap<String, Record> recordMap;
    HashMap<String, Policy> policyMap;
    private final String keyPrefixRegex = ".*::\\z";
    private final String keyPrefixGroup = "(.*::)(.*)";


    public BucketManager(){
        this.recordMap = new HashMap<>();
        this.policyMap = new HashMap<>();
    }
    public void putPolicy(Policy policy, String keyPrefix){
        validateKeyFormat(keyPrefix);
        policyMap.put(keyPrefix, policy);
    }

    private void throwInvalidKeyException(String key) throws InvalidKeyFormatException {
        log.error(String.format("Invalid key %s", key));
        throw new InvalidKeyFormatException(String.format("Invalid key %s", key));
    }

    @Override
    public void insert(String key) throws InvalidKeyFormatException{
        log.debug(String.format("getting policy for key %s", key));
        String keyPrefix = getKeyPrefix(key);
        Policy policy = null;
        Record record;
        if(policyMap.containsKey(keyPrefix)){
            policy = policyMap.get(keyPrefix);
        }
        else{
            throwInvalidKeyException(key);
        }
        if(recordMap.containsKey(key)){
            record = recordMap.get(key);
        }
        else{
            record = new Record();
        }
        log.debug("check if we can proceed for key: " + key);
        validateAndInsert(policy, record, key);
    }

    private String getKeyPrefix(String key) throws InvalidKeyFormatException {
        Pattern pattern = Pattern.compile(keyPrefixGroup);
        Matcher matcher = pattern.matcher(key);
        String keyPrefix;
        if(matcher.find()) {
            keyPrefix = matcher.group(1);
        }
        else{
            String msg = String.format("Invalid key %s", key);
            log.error(msg);
            throw new InvalidKeyFormatException(msg);
        }
        return keyPrefix;
    }

    private void validateAndInsert(Policy policy, Record record, String key) throws BucketLeakedException{

        if(Instant.now().getEpochSecond() > record.getFistInsertionTime() + policy.getBucketWindow()){
            log.debug(String.format("Reseting the key since the window is elapsed %s and inserting a new record", key));
            record.reset();
            this.recordMap.put(key, record);
        }
        else if(record.getCount() < policy.getMaxEntris()){
            log.debug(String.format("Inserting the record in key %s", key));
            record.increaseCount();
            this.recordMap.put(key, record);
        }
        else{
            String msg = String.format("Unable to insert, max count reached for key %s", key);
            log.debug(msg);
            throw new BucketLeakedException(msg);
        }
    }

    private void validateKeyFormat(String key) throws InvalidKeyFormatException{
        Pattern pattern = Pattern.compile(this.keyPrefixRegex);
        Matcher matcher = pattern.matcher(key);
        if(matcher.matches()){
            log.debug(String.format("valid key %s", key));
        }
        else{
            throwInvalidKeyException(key);
        }
    }
}
