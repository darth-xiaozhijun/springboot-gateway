package com.springboot.core.service.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.api.service.ICostService;
import com.springboot.api.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Resource
	private ICostService costService;

	@Override
	public Integer getCost(int a) {
		return costService.add(a);
	}

}
