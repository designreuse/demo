package com.ozh.module.shoppingcart.domain;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozh on 2016/3/10.
 */
public class ShoppingCart {
    private static final long serialVersionUID = 1L;

    @Id
    private String uniqueKey;//购物车唯一的标识
    private Date lastModifyTime;//最近一次改变时间

    private Integer userId;
    //购物车商品
    private List<CartItem> items = new ArrayList<CartItem>();
    /**
     * 运费
     */
    private Double freightAmount = 0D;
    /**
     * 商品总金额，商品行金额汇总
     */
    private Double productTotalAmount = 0D;
    /**
     * 总金额 = 运费 + 商品总金额 - 各种折扣金额
     */
    private Double orderTotalAmount = 0D;


    public ShoppingCart(String uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.lastModifyTime = new Date();
    }
    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Double getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(Double productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }

    public CartItem getItem(Integer skuId) {
        for (CartItem item : getItems()) {
            if (item.getSkuId().equals(skuId)) {
                return item;
            }
        }
        return null;
    }

    public CartItem getItem(String itemKey) {
        for (CartItem item : getItems()) {
            if (item.getItemKey().equals(itemKey)) {
                return item;
            }
        }
        return null;
    }
}
