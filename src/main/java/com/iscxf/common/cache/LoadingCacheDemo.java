package com.iscxf.common.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.iscxf.common.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxf
 * Created on 2020/5/6
 */
@Component
@Slf4j
public class LoadingCacheDemo {

    private LoadingCache<String, String> loadingCache;

    //服务启动的时候初始化value
    @PostConstruct
    private void init(){
        loadingCache = CacheBuilder.newBuilder().
                expireAfterWrite(30, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return StringUtils.EMPTY;
            }
        });
    }

    private Result checkCache(String key) {
        if (StringUtils.isEmpty(key)){
            return Result.newError(-1, "trace key is null! ");
        }
        try {
            String cacheString = loadingCache.get(key);
            log.info("trace get key value:[{}]", cacheString);
            return Result.newSuccess();
        } catch (ExecutionException e) {
            log.error("trace checkCache error! key:[{}], e:", key, e);
            return Result.newError(-1, e.getMessage());
        }
    }

    //清除缓存
    private void invalidateCache(String nameSpace, String key) {
        if (StringUtils.isEmpty(key)){
            log.error("trace invalidateCache key is null!");
            return;
        }
        loadingCache.invalidate(nameSpace.concat(key));
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void task(){
        loadingCache.put("sss", "cccc");
        checkCache("sss");
        log.info("trace invalidate key 1");
        loadingCache.invalidate("sss");
        checkCache("sss");
        log.info("trace invalidate key 2");
        loadingCache.invalidate("sss");
    }
}
