package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EnumTest {
   static int num = 0;
   static Object ob =new Object();

    public static void main(String[] args) {
        String tt = "opop";
        Optional.ofNullable(tt)
                .ifPresent(System.out::println);


    }
}
