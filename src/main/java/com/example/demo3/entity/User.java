package com.example.demo3.entity;

public class User {
    private String mmid;
    private String mpwd;
    private String mname;

    public String getMmid() {
        return mmid;
    }

    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    public String getMpwd() {
        return mpwd;
    }

    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "User{" +
                "mmid='" + mmid + '\'' +
                ", mpwd='" + mpwd + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }
}
