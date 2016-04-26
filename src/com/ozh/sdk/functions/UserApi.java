package com.ozh.sdk.functions;

import com.ozh.common.utils.BusinessException;
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

    /**
     * 取当前登录用户
     *
     * @return
     * @throws BusinessException
     */
    public static UserVo getLoginUser() throws BusinessException {
        return SpringContextHolder.getBean(UserApiService.class).getLoginUser();
    }
}
