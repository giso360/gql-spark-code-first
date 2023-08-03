package com.wh.db;

import java.util.ArrayList;
import java.util.List;

public enum WhSingle {

    INSTANCE;

    private List<String> a = new ArrayList<>();

    private String name;

    public void addAddr(String addr){
        a.add(addr);
    }

    public void removeAddr(String addr) {
        a.remove(addr);
    }

    public String getA(int i) {
        return a.get(i);
    }

    public String getName() {
        return name;
    }
}
