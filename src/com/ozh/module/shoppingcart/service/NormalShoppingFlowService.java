package com.ozh.module.shoppingcart.service;

import com.ozh.common.Global;
import com.ozh.common.utils.BusinessException;
import com.ozh.common.utils.ShoppingCartProcessing;
import com.ozh.core.service.ShoppingCartListService;
import com.ozh.module.shoppingcart.domain.ShoppingCart;
import com.ozh.utils.SpringContextHolder;
import com.ozh.web.WebContextFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ozh on 2016/3/10.
 */
@Service
@Transactional
public class NormalShoppingFlowService{
    public void addItem(Integer productId,Integer quantity, Integer userId) throws BusinessException {
        ShoppingCart cart = getCart(userId);
       ShoppingCartProcessing.addItem(cart, productId, quantity);
//        ResolverUtils.clacCartMisc(cart);
//        ServiceManager.shoppingCartStoreService.saveCart(cart);
        saveCart(genUniqueKey(userId),cart);
    }
    public ShoppingCart getCart(Integer userId) {
        String uniqueKey = genUniqueKey(userId);
        ShoppingCart cart = (ShoppingCart) WebContextFactory.getWebContext().getSessionAttr(uniqueKey);
        if(cart!=null){
            cart.setUserId(userId);
        }else {
            return createCart(userId,uniqueKey);
        }
        return cart;
    }

    public ShoppingCart createCart(Integer userId,String uniqueKey) {
        ShoppingCart cart = new ShoppingCart(genUniqueKey(userId));
        SpringContextHolder.getBean(ShoppingCartListService.class).getShoppingCartFromTable(cart,userId);
        cart.setUserId(userId);
        return saveCart(uniqueKey,cart);
    }
    public String genUniqueKey(Integer userId) {
        String sessionKey = Global.SHOPPINGCART + "_"  + userId;
        String uniqueKey = null;
        uniqueKey = (String) WebContextFactory.getWebContext().getSessionAttr(sessionKey);
        if (uniqueKey == null) {
            uniqueKey = UUID.randomUUID().toString();
            WebContextFactory.getWebContext().setSessionAttr(sessionKey, uniqueKey);
        }
        return uniqueKey;
    }
    public ShoppingCart saveCart(String uniqueKey,ShoppingCart cart) {
        cart.setLastModifyTime(new Date());
        ShoppingCartProcessing.cartProcessing(cart);
        WebContextFactory.getWebContext().setSessionAttr(uniqueKey, cart);
        return cart;
    }
}
