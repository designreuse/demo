package com.ozh.module.shoppingcart.domain;

import java.util.UUID;

/**
 * Created by ozh on 2016/3/10.
 */
public class CartItem {
    private Integer skuId;
    private Integer quantity;//数量
    private String name;//商品名称
    private String specName;//规格名称
    private String itemKey = UUID.randomUUID().toString();
    private String imgUrl;//商品图片地址
    /** 商品总金额 = 单价 * 数量*/
    private Double productTotalAmount;
    /** 商品单价*/
    private Double productUnitPrice;

    //限购数量
    private Integer limitBuy;
    public CartItem(Integer skuId,Integer quantity,String name,String specName,Double productUnitPrice,Double productTotalAmount){
        this.skuId = skuId;
        this.quantity = quantity;
        this.name = name;
        this.specName = specName;
        this.productUnitPrice = productUnitPrice;
        this.productTotalAmount = productTotalAmount;
    }

    public CartItem() {

    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public Double getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(Double productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public Double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(Double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public Integer getLimitBuy() {
        return limitBuy;
    }

    public void setLimitBuy(Integer limitBuy) {
        this.limitBuy = limitBuy;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
