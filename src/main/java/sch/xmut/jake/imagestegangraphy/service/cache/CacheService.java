package sch.xmut.jake.imagestegangraphy.service.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.http.response.BaseResponse;
import sch.xmut.jake.cache.apicache.http.response.CacheResponse;
import sch.xmut.jake.cache.apicache.service.serviceInterface.KeyServiceInterface;
import sch.xmut.jake.cache.apicache.service.serviceInterface.StringCacheServiceInterface;

/**
 * Created by jake.lin on 2019/12/26
 */
@Service
public class CacheService {
    @Reference
    private StringCacheServiceInterface stringCacheServiceInterface;
    @Reference
    private KeyServiceInterface keyServiceInterface;

    //设置key
    public BaseResponse stringAdd(CacheRequest cacheRequest) {
        return stringCacheServiceInterface.add(cacheRequest);
    }

    //获取key
    public CacheResponse stringGet(CacheRequest cacheRequest) {
        return stringCacheServiceInterface.get(cacheRequest);
    }

    //设置key的生存时间
    public BaseResponse keySetTime(CacheRequest cacheRequest) {
        return keyServiceInterface.setTime(cacheRequest);
    }

    //删除缓存数据
    public BaseResponse stringDelete(CacheRequest cacheRequest) {
        return stringCacheServiceInterface.delete(cacheRequest);
    }
}
