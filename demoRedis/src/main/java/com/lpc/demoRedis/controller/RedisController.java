package com.lpc.demoRedis.controller;

import com.lpc.demoRedis.entity.User;
import com.lpc.demoRedis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lpc
 * @Date: 2018-09-17 15:39
 */

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private  RedisService redisService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 设置Str缓存
     *
     * @param key
     * @param val
     * @return
     */
    @RequestMapping(value = "setStr")
    public String setStr(String key, String val) {
        try {
            redisService.setStr(key, val);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("setStr: e = "+e.toString());
            return "error";
        }
    }

    /**
     * 根据key查询Str缓存
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "getStr")
    public String getStr(String key) {
        return redisService.getStr(key);
    }

    /**
     * 根据key产出Str缓存
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "delStr")
    public String delStr(String key) {
        try {
            redisService.del(key);
            return "success";
        } catch (Exception e) {
            logger.error("delStr: e = "+e.toString());
            return "error";
        }
    }

    /**
     * 设置obj缓存
     *
     * @param key
     * @param user
     * @return
     */
    @RequestMapping(value = "setObj")
    public String setObj(String key, User user) {
        try {
            redisService.setObj(key, user);
            return "success";
        } catch (Exception e) {
            logger.error("setObj: e = "+e.toString());
            return "error";
        }
    }

    /**
     * 获取obj缓存
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "getObj")
    public Object getObj(String key) {
        return redisService.getObj(key);
    }

    /**
     * 删除obj缓存
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "delObj")
    public Object delObj(String key) {
        try {
            redisService.delObj(key);
            return "success";
        } catch (Exception e) {
            logger.error("delObj: e = "+e.toString());
            return "error";
        }
    }
}