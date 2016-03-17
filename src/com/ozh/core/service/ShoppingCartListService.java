package com.ozh.core.service;


import com.ozh.core.entity.ShoppingCartList;
import com.ozh.common.service.IBaseService;
import com.ozh.module.shoppingcart.domain.CartItem;
import com.ozh.module.shoppingcart.domain.ShoppingCart;

import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShoppingCartListService extends IBaseService<ShoppingCartList, Long>{
    List<CartItem> findCartItemByUserId(Integer userId);

    void getShoppingCartFromTable(ShoppingCart cart,Integer userId);

}
