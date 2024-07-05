package com.example.demo3.entity;

public class Order {
    private Integer oid;
    private String ocontent;
    private String createtime;
    private String overtime;
    private String ostatus;
    private String remark;
    private String sum;
    private String cid;

    private String way;

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOcontent() {
        return ocontent;
    }

    public void setOcontent(String ocontent) {
        this.ocontent = ocontent;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ocontent='" + ocontent + '\'' +
                ", createtime='" + createtime + '\'' +
                ", overtime='" + overtime + '\'' +
                ", ostatus='" + ostatus + '\'' +
                ", remark='" + remark + '\'' +
                ", sum='" + sum + '\'' +
                ", cid='" + cid + '\'' +
                ", way='" + way + '\'' +
                '}';
    }
}
