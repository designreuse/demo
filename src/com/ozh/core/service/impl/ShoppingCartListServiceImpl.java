package com.ozh.core.service.impl;


import com.ozh.common.service.IBaseService;
import com.ozh.common.service.impl.AbstractBaseService;
import com.ozh.common.utils.ShoppingCartProcessing;
import com.ozh.core.entity.Product;
import com.ozh.core.entity.ShoppingCartList;
import com.ozh.core.repository.ShoppingCartListRepository;
import com.ozh.core.service.ProductService;
import com.ozh.core.service.ShoppingCartListService;
import com.ozh.module.shoppingcart.domain.CartItem;
import com.ozh.module.shoppingcart.domain.ShoppingCart;
import com.ozh.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ShoppingCartListServiceImpl extends AbstractBaseService<ShoppingCartList, Long> implements ShoppingCartListService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private ShoppingCartListRepository getSysRoleRepository() {
        return (ShoppingCartListRepository) baseRepository;
    }

    @Override
    public List<CartItem> findCartItemByUserId(Integer userId) {
        List<ShoppingCartList> lists = getSysRoleRepository().findByUserIdAndIsDelete(userId, "N");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        for(ShoppingCartList cartList:lists){
            Product product = SpringContextHolder.getBean(ProductService.class).findOne(cartList.getProductId());
            String name = product.getProductNm();
            String specName = "";
            Double productUnitPrice = product.getMarketPrice();
            Integer quantuty = cartList.getQuantity();
            CartItem cartItem = new CartItem(cartList.getProductId().intValue(),quantuty,name,specName,productUnitPrice,quantuty*productUnitPrice);
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    @Override
    public void getShoppingCartFromTable(ShoppingCart cart, Integer userId) {
        List<ShoppingCartList> lists = getSysRoleRepository().findByUserIdAndIsDelete(userId, "N");
        for(ShoppingCartList cartList:lists){
            ShoppingCartProcessing.addItem(cart,cartList.getProductId().intValue(),cartList.getQuantity());
        }
    }

}
