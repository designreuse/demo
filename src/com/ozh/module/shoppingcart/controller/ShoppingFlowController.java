package com.ozh.module.shoppingcart.controller;

import com.ozh.core.entity.SysUser;
import com.ozh.module.shoppingcart.service.NormalShoppingFlowService;
import com.ozh.utils.SpringContextHolder;
import com.ozh.web.WebContextFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ozh on 2016/3/10.
 */
@Controller
@RequestMapping("/cart")
public class ShoppingFlowController  {
    @RequestMapping(value = "/add")
    public void addItem(ModelMap model,Integer quantity,Integer productId) {
        SysUser sysUser = WebContextFactory.getWebContext().getFrontEndUser();
        if (sysUser == null){
            model.put("error", "login");
            return;
        }
        SpringContextHolder.getBean(NormalShoppingFlowService.class).addItem(productId,quantity,sysUser.getId().intValue());

    }
}
