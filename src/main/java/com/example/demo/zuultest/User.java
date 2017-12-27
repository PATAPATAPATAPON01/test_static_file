package com.example.demo.zuultest;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.ByteStreams;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by PataPon on 2017/12/6.
 */
public class User {


    public static void main(String[] args) throws ExecutionException {
        LoadingCache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(100)
                .expireAfterWrite(2, TimeUnit.DAYS).build(new CacheLoader<Object, Object>() {
                    @Override
                    public Object load(Object key) {
                        return 1;
                    }
                });

//        cache.put("myKey","200");
        Object myKey = cache.getUnchecked("myKey");
        System.out.println(myKey);
        System.out.println(cache.getUnchecked("myKey"));

        System.out.println(cache.get("key2", new Callable<Object>() {

            @Override
            public Object call() {
                return 200;
            }
        }));
    }
}
