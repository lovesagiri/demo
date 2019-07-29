package com.ThreadTest;

import javax.jnlp.IntegrationService;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

public class Optest {
    static final List<Integer> list = Arrays.asList(0,1,2,3,4);
    static final List<Integer> param = Arrays.asList(1,2,3);

    public static void main(String[] args) {
        List<Integer> temp = filterArray(param);
        System.out.println(temp.toArray());

    }

    private static List<Integer> filterArray(List<Integer> param) {
        if (Optional.ofNullable(param).isPresent()){
            list.removeAll(param);
            return  list;
        }
        return list;

    }
}
