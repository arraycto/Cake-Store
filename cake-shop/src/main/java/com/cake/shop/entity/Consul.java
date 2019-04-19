package com.cake.shop.entity;

import java.io.Serializable;

/**
 * 管理员豆
 */
public class Consul implements Serializable {
    public Integer csid;
    public String account;
    public String cspassword;

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCspassword() {
        return cspassword;
    }

    public void setCspassword(String cspassword) {
        this.cspassword = cspassword;
    }

    @Override
    public String toString() {
        return "Consul{" +
                "csid=" + csid +
                ", account='" + account + '\'' +
                ", cspassword='" + cspassword + '\'' +
                '}';
    }
}
