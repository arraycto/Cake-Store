package com.cake.shop.entity;

import java.io.Serializable;

/**
 * 蛋糕实体豆
 */
public class AngelCake implements Serializable {
    private Integer acid;
    private String cakename;
    private String caketype;
    private Integer price;
    private String supplier;
    private Integer amount;

    public Integer getAcid() {
        return acid;
    }

    public void setAcid(Integer acid) {
        this.acid = acid;
    }

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
    }

    public String getCaketype() {
        return caketype;
    }

    public void setCaketype(String caketype) {
        this.caketype = caketype;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AngelCake{" +
                "acid=" + acid +
                ", cakename='" + cakename + '\'' +
                ", caketype='" + caketype + '\'' +
                ", price=" + price +
                ", supplier='" + supplier + '\'' +
                ", amount=" + amount +
                '}';
    }
}
