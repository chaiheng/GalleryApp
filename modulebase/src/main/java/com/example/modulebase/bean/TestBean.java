package com.example.modulebase.bean;

public class TestBean {
    public TestBean(String id, String name, String writer, int res, String content, int type) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.res = res;
        this.content = content;
        this.type = type;
    }

    private String id;
    private String name;
    private String writer;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    private int res;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private String content;
    private int type;
}
