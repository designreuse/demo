package com.ozh.core.demo.repository.impl;

import com.ozh.core.demo.entity.UserInfo;
import com.ozh.core.demo.repository.UserInfoRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.ozh.core.demo.entity.UserInfo;
import com.ozh.core.demo.repository.UserInfoRepositoryCustom;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kosam on 2016/2/28.
 * 这里只需要实现 RepositoryCustom 里面的接口
 */
public class UserInfoRepositoryImpl implements UserInfoRepositoryCustom {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<UserInfo> query(Pageable pageable, String userAccount) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from t_user_info a where 1=1 ");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(userAccount)) {
            sql.append(" AND a.USER_ACCOUNT LIKE :userAccount");
            paramMap.put(UserInfo.USER_ACCOUNT, "%" +userAccount+ "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Long total = new NamedParameterJdbcTemplate(this.jdbcTemplate).queryForObject("select count(*)"+ sql, paramMap, Long.class);
        List<UserInfo> resultList = new NamedParameterJdbcTemplate(this.jdbcTemplate)
                .query("select a.* " + QueryUtils.applySorting(sql.toString(), pageable.getSort(), "a") + " limit " + firstIdx + "," + pageSize, paramMap, new BeanPropertyRowMapper(UserInfo.class));

        return new PageImpl<UserInfo>(resultList, pageable, total);
    }
}
