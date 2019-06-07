package com.springboot.core.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.api.service.ICostService;

@Service
public class CostServiceImpl implements ICostService{

	/**
     * 假设之前总花费了100
     */
    private final Integer totalCost = 1000;

    /**
     * 之前总和 加上 最近一笔
     * @param cost
     * @return
     */
    @Override
    public Integer add(int cost) {
        return totalCost + cost;
    }

}
