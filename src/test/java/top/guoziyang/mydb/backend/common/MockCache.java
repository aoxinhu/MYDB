package top.guoziyang.mydb.backend.common;

import top.aoxinhu.mydb.backend.common.AbstractCache;

public class MockCache extends AbstractCache<Long> {

    public MockCache() {
        super(50);
    }

    @Override
    protected Long getForCache(long key) throws Exception {
        return key;
    }

    @Override
    protected void releaseForCache(Long obj) {}
    
}
