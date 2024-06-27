package com.example.demo3.entity;

public class Review {

    private String rid;
    private String rscore;
    private String rcontent;
    private String rtime;
    private String cid;


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRscore() {
        return rscore;
    }

    public void setRscore(String rscore) {
        this.rscore = rscore;
    }

    public String getRcontent() {
        return rcontent;
    }

    public void setRcontent(String rcontent) {
        this.rcontent = rcontent;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rid='" + rid + '\'' +
                ", rscore='" + rscore + '\'' +
                ", rcontent='" + rcontent + '\'' +
                ", rtime='" + rtime + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
