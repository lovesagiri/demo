package com.msgpack;

import org.msgpack.annotation.Message;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/26 10:48
 * @Version 1.0
 **/
@Message
public class User {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
