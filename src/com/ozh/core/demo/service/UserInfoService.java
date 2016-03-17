package com.ozh.core.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ozh.common.service.IBaseService;
import com.ozh.core.demo.entity.UserInfo;

/**
 * Created by ksoam on 2016/2/27.
 */
public interface UserInfoService extends IBaseService<UserInfo, Long> {

    /**
     * 根据用户名 分页查询
     * @param pageable
     * @param userAccount
     * @return
     */
    Page<UserInfo> query(Pageable pageable, String userAccount);
}
