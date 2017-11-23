package com.log.controller;

import com.alibaba.fastjson.JSONArray;
import com.log.common.PayBusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wodezuiaishinageren on 2017/11/23.
 * 接收消息
 */
@RestController("/")
public class ReceivePayResController {
    /**
     * 测试
     * @param name
     * @return
     */
    @RequestMapping(value="index")
    public String recevice(String name){
        System.out.println("测试进来的！！！");
        List<Integer> integers= Arrays.asList(1,2,2);
        return JSONArray.toJSONString(integers);
    }

    /**
     * 测试异常
     * @param name
     */
    @RequestMapping(value="exception")
    public void  exception(String name) throws PayBusinessException {
        throw  new PayBusinessException("0001","自定义业务异常");
    }
}
