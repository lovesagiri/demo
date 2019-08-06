package com.fan;

public class GenTest<T> {

    private T code;

    public T getCode() {
        return code;
    }

    public void setCode(T code) {
        this.code = code;
    }

    //泛型方法
    public void changge(GenTest<T> tem){
        System.out.println(tem.getCode());
    }
}
