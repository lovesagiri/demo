package com.fan;

import com.msgpack.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestF {

    public static void main(String[] args) {
        List<User> list = Arrays.asList(new User("zhangsan", 18),
                new User("lisi", 21),
                new User("wangwu", 19),
                new User("zhaoliu", 20));
        list.stream().collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);
        System.out.println(list.stream().map(x -> x.getAge()).reduce(Integer::sum));
        System.out.println(list.stream().map(x -> x.getAge()).reduce(1, Integer::sum));
    }

}
