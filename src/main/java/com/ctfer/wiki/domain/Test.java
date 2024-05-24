package com.ctfer.wiki.domain;

public class Test {

    private Integer id;

    private String name;

    private String password;

//    alt + insert select getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
// 实体类对应数据库表 domain 和 entity一样
