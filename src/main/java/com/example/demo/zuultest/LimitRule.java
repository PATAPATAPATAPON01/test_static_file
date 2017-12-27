package com.example.demo.zuultest;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class LimitRule {


    private Integer refreshInterval = 60;
    private Integer limit;

    public Integer getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Integer refreshInterval) {
        System.out.println("LimitRule.setRefreshInterval");
        this.refreshInterval = refreshInterval;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        System.out.println("LimitRule.setLimit");
        this.limit = limit;
    }


}
