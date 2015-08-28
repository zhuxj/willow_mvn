/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2013 
 * 日    期：13-1-9
 */
package com.willow.platform.cache;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class SimpleCacheable<T> implements Cacheable<T> {
    private T value;
    private int version = 0;
    private long cacheUpdateTime;
    private int localCacheTimeToLive = Cacheable.LOCAL_CACHE_DEFAUL_TIME_TO_LIVE;
    private int remoteCacheTimetoLive = Cacheable.REMOTE_CACHE_DEFAUL_TIME_TO_LIVE;

    public SimpleCacheable(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public int getLocalCacheTimeToLive() {
        return this.localCacheTimeToLive;
    }

    @Override
    public int getRemoteCacheTimeToLive() {
        return this.remoteCacheTimetoLive;
    }

    @Override
    public long getCacheUpdateTime() {
        return this.cacheUpdateTime;
    }

    @Override
    public void setCacheUpdateTime(long cacheUpdateTime) {
        this.cacheUpdateTime = cacheUpdateTime;
    }
}
