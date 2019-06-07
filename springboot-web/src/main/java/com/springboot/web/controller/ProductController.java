package com.springboot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.api.service.IProductService;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

	@Reference
    private IProductService productService;

    /**
     * 添加完 返回总共消费
     * @param a
     * @return
     */
    @RequestMapping(value = "/add.do",method = RequestMethod.GET)
    public String getCost(int a){
        return "该产品总共消费 ："+productService.getCost(a);
    }
}
