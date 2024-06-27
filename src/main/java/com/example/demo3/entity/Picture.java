package com.example.demo3.entity;

public class Picture {
    private int picid;
    private String picname;
    private String url;

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPicid() {
        return picid;
    }

    public void setPicid(int picid) {
        this.picid = picid;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "picid=" + picid +
                ", picname='" + picname + '\'' +
                ", url='" + url + '\'' +
                ", number=" + number +
                '}';
    }
}
