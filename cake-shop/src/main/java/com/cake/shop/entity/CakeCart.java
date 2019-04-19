package com.cake.shop.entity;

import java.io.Serializable;

/**
 * 购物车实体豆
 */
public class CakeCart implements Serializable {
    private Integer ccid;
    private String ccname;
    private String cctype;
    private Integer ccprice;
    private String ccsupplier;
    private Integer cuid;

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Integer getCcid() {
        return ccid;
    }

    public void setCcid(Integer ccid) {
        this.ccid = ccid;
    }

    public String getCcname() {
        return ccname;
    }

    public void setCcname(String ccname) {
        this.ccname = ccname;
    }

    public String getCctype() {
        return cctype;
    }

    public void setCctype(String cctype) {
        this.cctype = cctype;
    }

    public Integer getCcprice() {
        return ccprice;
    }

    public void setCcprice(Integer ccprice) {
        this.ccprice = ccprice;
    }

    public String getCcsupplier() {
        return ccsupplier;
    }

    public void setCcsupplier(String ccsupplier) {
        this.ccsupplier = ccsupplier;
    }

    @Override
    public String toString() {
        return "CakeCart{" +
                "ccid=" + ccid +
                ", ccname='" + ccname + '\'' +
                ", cctype='" + cctype + '\'' +
                ", ccprice=" + ccprice +
                ", ccsupplier='" + ccsupplier + '\'' +
                ", cuid=" + cuid +
                '}';
    }
}
