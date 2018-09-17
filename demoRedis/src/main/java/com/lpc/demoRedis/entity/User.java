package com.lpc.demoRedis.entity;

import java.io.Serializable;

/**
 * @Author: lpc
 * @Date: 2018-09-17 15:27
 */
public class User implements Serializable {

    private String id;
    private String name;

    public User(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
