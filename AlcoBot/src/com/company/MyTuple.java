package com.company;

public class MyTuple implements Comparable<MyTuple> {
    private Integer key;
    private String value;

    public MyTuple(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int compareTo(MyTuple t) {
        return key.compareTo(t.getKey());
    }
}
