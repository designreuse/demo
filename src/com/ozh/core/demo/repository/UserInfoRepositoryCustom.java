package com.ozh.core.demo.repository;

import com.ozh.core.demo.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kosam on 2016/2/28.
 * 自定义接口 ： 需要实现的 持久层的接口 定义在这个接口中
 */
public interface UserInfoRepositoryCustom {

    Page<UserInfo> query(Pageable pageable, String userAccount);
}
