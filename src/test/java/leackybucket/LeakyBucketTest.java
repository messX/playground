package leackybucket;

import leakybucket.BucketManager;
import leakybucket.Policy;
import leakybucket.Record;
import leakybucket.exception.BucketLeakedException;
import leakybucket.exception.InvalidKeyFormatException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Slf4j
public class LeakyBucketTest {
    BucketManager bucketManager;
    @Before
    public void setUp(){
      log.debug("setting up the tests");
      this.bucketManager = new BucketManager();
    }

    @Test
    public void checkInvalidKeyFormat(){
        String keyPrefix = "Test123";
        log.info("Should throw invalid key exception");
        assertThrows(InvalidKeyFormatException.class, () -> this.bucketManager.putPolicy(getBasicPolicy(), keyPrefix));
    }

    @Test
    public void checkValidKeyFormat(){
        String keyPrefix = "Test123::";
        log.info("Should not throw invalid key exception");
        Exception exp = null;
        try{
            this.bucketManager.putPolicy(getBasicPolicy(), keyPrefix);
        }
        catch (InvalidKeyFormatException ex){
            exp = ex;
        }
        assertEquals(null, exp);

    }

    @Test
    public void checkIfBucketLeaked(){
        String keyPrefix = "Test123::";
        this.bucketManager.putPolicy(getBasicPolicy(), keyPrefix);
        int testRecCount = 4;
        String key1 = keyPrefix + "_1";
        String key2 = keyPrefix + "_2";
        for (int i = 0; i < testRecCount; i++) {
            if(i < this.bucketManager.getPolicyMap().get(keyPrefix).getMaxEntris()) {
                this.bucketManager.insert(key1);
            }
            else{
                log.info("Should throw Bucket leak exception");
                assertThrows(BucketLeakedException.class, () -> this.bucketManager.insert(key1));
            }
        }
        for (int i = 0; i < this.bucketManager.getPolicyMap().get(keyPrefix).getMaxEntris(); i++) {
            this.bucketManager.insert(key2);
        }
    }

    private Policy getBasicPolicy(){
        return new Policy(100, 3);
    }

    private List<Record> getNRecords(int n){
        List<Record> recordList = new ArrayList<>();
        for(int i=0;i<n;i++){
            Record rec = new Record();
            recordList.add(rec);
        }
        return recordList;
    }

}
