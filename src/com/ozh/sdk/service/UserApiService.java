package com.ozh.sdk.service;

import com.ozh.module.demo.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * service 只读
 * Created by tgx on 2015/5/27.
 */
@Service
@Transactional(readOnly = true)
public class UserApiService {

    public UserVo getUserInfo() {
        UserVo userVo = new UserVo();
        userVo.setUserName("kosam");
        userVo.setAge(25);
        return userVo;
    }
}
