package com.wsj.magic.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssssssss.script.MagicScript;

import javax.annotation.Resource;

@RestController
public class TmpController {
    @Resource
    private ApplicationContext applicationContext;
    @GetMapping("test")
    public String test() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //打印
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
//        System.out.println(beanDefinitionNames);
        return "test";
    }
}
