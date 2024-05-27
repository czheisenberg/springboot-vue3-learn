package com.ctfer.wiki.req;

public class PageReq {
//    page 页数
    private int page;
//    size 条数
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    toString() 日志打印
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageReq{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append('}');

        return sb.toString();
    }
}