package com.ozh.module.demo.controller;

import com.ozh.core.demo.service.UserInfoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ozh.core.demo.entity.UserInfo;
import com.ozh.utils.SpringContextHolder;

/**
 * Created by tgx on 2015/5/11.
 */
@Controller
@RequestMapping(value = "demo")
public class DemoController {

    private final static String SUCCESS = "success";

    @RequestMapping(value = "/demo.html", method = RequestMethod.GET)
    public String toDemo() {
        return "/b2c/demo";
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public String getNameAndPassword(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, String name, String password) {

        System.out.println("name=" +name+ "," + "password=" +password);
        model.put("success", true);
        model.put("userInfo", name +","+ password);
        UserInfo userInfo = new UserInfo();
        userInfo.setUseName("first Name");
        userInfo.setUserAccount("kosam16");
        userInfo.setUserPassword("123456");
        UserInfoService userInfoService = SpringContextHolder.getBean(UserInfoService.class);
        userInfoService.save(userInfo);
       // Page<UserInfo> result = userInfoService.query(pageable, "am1");
        return SUCCESS;
    }
}
