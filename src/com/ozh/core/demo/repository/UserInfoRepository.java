package com.ozh.core.demo.repository;

import com.ozh.common.repository.IBaseRepository;
import com.ozh.core.demo.entity.UserInfo;

/**
 * Created by ozh on 2016/2/28.
 * 这里定义的是不需要实现的 持久层接口，
 * 接口需要严格按照 JPA 规范
 */
public interface UserInfoRepository extends IBaseRepository<UserInfo, Long>, UserInfoRepositoryCustom {
}
