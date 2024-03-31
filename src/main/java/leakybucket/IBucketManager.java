package leakybucket;

import leakybucket.exception.BucketLeakedException;

public interface IBucketManager {
    public void insert(String key);
}
