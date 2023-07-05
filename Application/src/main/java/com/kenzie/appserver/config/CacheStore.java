//package com.kenzie.appserver.config;
//
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import com.kenzie.appserver.service.model.Parent;
//
//public class CacheStore {
//    private final Cache<String, Parent> cache;
//    private String parentUsername;
//    public CacheStore() {
//        // initalize the cache
//        this.cache = CacheBuilder.newBuilder()
//                .build();
//
//    }
//    public CacheStore(String parentUsername) {
//        // initalize the cache
//        this.cache = CacheBuilder.newBuilder()
//                .build();
//this.parentUsername = parentUsername;
//    }
//
//    public Parent get(String parentUsername) {
//        // Write your code here
//        // Retrieve and return the concert
//        return cache.getIfPresent(parentUsername);
//    }
//
//    public void evict(String key) {
//        // Write your code here
//        // Invalidate/evict the concert from cache
//        if(key != null) {
//            cache.invalidate(key);
//        }
//    }
//
//    public void add(String key, Parent value) {
//        // Write your code here
//        // Add concert to cache
//        if(key != null && value != null) {
//            cache.put(key,value);
//        }
//    }
//}
