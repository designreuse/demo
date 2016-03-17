package com.ozh.common.utils;

import com.ozh.core.entity.Product;
import com.ozh.core.service.ProductService;
import com.ozh.module.shoppingcart.domain.CartItem;
import com.ozh.module.shoppingcart.domain.ShoppingCart;
import com.ozh.utils.SpringContextHolder;

/**
 * Created by OU on 2016/3/15.
 */
public class ShoppingCartProcessing {

    public static void cartProcessing(ShoppingCart cart){
        Double productTotalAmount = 0D;//总价
        Double freight = 0D;//运费
        for (CartItem cartItem : cart.getItems()) {
//            cartItem.setProductTotalAmount(cartItem.getQuantity()*cartItem.getProductUnitPrice());
            productTotalAmount +=cartItem.getProductTotalAmount();
        }
        productTotalAmount +=freight;
        cart.setProductTotalAmount(productTotalAmount);
    }

    public static void addItem(ShoppingCart cart, Integer productId, Integer quantity) {
        Product product = SpringContextHolder.getBean(ProductService.class).findOne(productId.longValue());
        CartItem cartItem = new CartItem();
        cartItem.setSkuId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setProductUnitPrice(product.getMarketPrice());
        cartItem.setProductTotalAmount(product.getMarketPrice()*quantity);

        cart.getItems().add(cartItem);
    }
}
