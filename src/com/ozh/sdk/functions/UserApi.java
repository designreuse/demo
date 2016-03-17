package com.ozh.sdk.functions;

import com.ozh.module.demo.vo.UserVo;
import com.ozh.sdk.service.UserApiService;
import com.ozh.utils.SpringContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by tgx on 2015/5/27.
 */
@Service
public class UserApi {

    private UserApi() {}

    public static UserVo getUserInf() {
        System.out.println(SpringContextHolder.getApplicationContext());
        return SpringContextHolder.getBean(UserApiService.class).getUserInfo();
    }
}
