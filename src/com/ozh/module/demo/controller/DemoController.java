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
@RequestMapping(value = "admin")
public class DemoController {

    private final static String SUCCESS = "success";
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String toIndex() {
        return "/admin/index";
    }

    @RequestMapping(value = "/home_top.html", method = RequestMethod.GET)
    public String toHomeTop() {
        return "/admin/top";
    }
    @RequestMapping(value = "/home_left.html", method = RequestMethod.GET)
    public String toHoneLeft() {
        return "/admin/left";
    }
    @RequestMapping(value = "/userUI.html", method = RequestMethod.GET)
    public String toUserUI() {
        return "/admin/user/userList";
    }


    @RequestMapping(value = "/bg.html", method = RequestMethod.GET)
    public String tobg() {
        return "/admin/common/bg";
    }

    @RequestMapping(value = "/welcome.html", method = RequestMethod.GET)
    public String toWelcome() {
        return "/admin/common/welcome";
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
