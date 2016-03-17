package com.ozh.core.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ozh.common.service.impl.AbstractBaseService;
import com.ozh.core.demo.entity.UserInfo;
import com.ozh.core.demo.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

/**
 * Created by ksoam on 2016/2/27.
 */
@Service
public class UserInfoServiceImpl extends AbstractBaseService<UserInfo, Long> implements UserInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private UserInfoRepository getUserInfoRepository() {
        return (UserInfoRepository) baseRepository;
    }

    @Override
    public Page<UserInfo> query(Pageable pageable, String userAccount) {

        return getUserInfoRepository().query(pageable, userAccount);
    }
}