package com.example.demo3.entity;

public class Customer {
    private String cid;
    private String cpwd;
    private String cname;
    private String csex;
    private String cbirth;
    private String cphone;

    private String cstatus;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCpwd() {
        return cpwd;
    }

    public void setCpwd(String cpwd) {
        this.cpwd = cpwd;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCsex() {
        return csex;
    }

    public void setCsex(String csex) {
        this.csex = csex;
    }

    public String getCbirth() {
        return cbirth;
    }

    public void setCbirth(String cbirth) {
        this.cbirth = cbirth;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid='" + cid + '\'' +
                ", cpwd='" + cpwd + '\'' +
                ", cname='" + cname + '\'' +
                ", csex='" + csex + '\'' +
                ", cbirth='" + cbirth + '\'' +
                ", cphone='" + cphone + '\'' +
                ", cstatus='" + cstatus + '\'' +
                '}';
    }
}
