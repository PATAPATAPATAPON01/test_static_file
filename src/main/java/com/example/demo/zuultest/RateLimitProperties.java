package com.example.demo.zuultest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PataPon on 2017/12/10.
 */
@ConfigurationProperties("pattern")
@Configuration
public class RateLimitProperties {

    private Map<PolicyType, LimitRule> defaultPolicy = new LinkedHashMap<>();


   private List<PolicyType> result = new ArrayList<>();

    public Map<PolicyType, LimitRule> getDefaultPolicy() {
        return defaultPolicy;
    }

    public List<PolicyType> getResult() {
        return result;
    }
//
//    public void setResult(List<String> result) {
//        this.result = result;
//    }
//    public void setDefaultPolicy(Map<PolicyType, LimitRule> defaultPolicy) {
//        System.out.println("RateLimitProperties.setDefaultPolicy");
//        this.defaultPolicy = defaultPolicy;
//    }
}
