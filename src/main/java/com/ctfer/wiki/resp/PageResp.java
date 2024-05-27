package com.ctfer.wiki.resp;

import java.util.List;

public class PageResp<T> {
//    total 总数
    private long total;
//    T 泛型 list 通用数据，不一定只放电子书，也可以放用户等其他数据，因此为泛型
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageResp{");
        sb.append("total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');

        return sb.toString();
    }
}