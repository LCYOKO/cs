package com.xiaomi.cs.pojo.entity;

import java.io.Serializable;
import java.util.List;

public class PageSerializable<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected long total;
    protected List<T> list;

    public PageSerializable() {
    }

    public PageSerializable(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String toString() {
        return "PageSerializable{total=" + this.total + ", list=" + this.list + '}';
    }
}
