package com.company;

public class MyTupple implements Comparable<MyTupple> {
    private Integer key;
    private String value;

    public MyTupple(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int compareTo(MyTupple t) {
        return key.compareTo(t.getKey());
    }
}
