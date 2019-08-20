package com.sta;

public enum TestEnum {
    UNKNOWN("未知设备"),
    SMOKE("烟感"),
    DOOR("门磁"),
    TEMP("温湿度"),
    WATER("水位"),
    GARBAGE("垃圾桶"),
    WINDOW("窗磁")
    ;

    public final String comment;

    TestEnum(final String comment) {
        this.comment = comment;
    }
}
