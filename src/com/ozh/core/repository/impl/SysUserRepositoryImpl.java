
package com.ozh.core.repository.impl;

import com.ozh.core.entity.SysUser;
import com.ozh.core.repository.SysUserRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysUserRepositoryImpl implements SysUserRepositoryCustom{

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<SysUser> findUserList(Pageable pageable, String search) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from sys_user a where 1=1 ");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(search)) {
            sql.append(" AND a.USER_NAME LIKE :userName");
            sql.append(" OR a.LOGIN_ID LIKE :loginId");
            sql.append(" OR a.USER_MOBILE LIKE :userMobile");
            sql.append(" OR a.USER_TEL LIKE :userTel");
            sql.append(" OR a.USER_EMAIL LIKE :userEmail");
            paramMap.put(SysUser.USER_NAME, "%" + search + "%");
            paramMap.put(SysUser.LOGIN_ID, "%" + search + "%");
            paramMap.put(SysUser.USER_MOBILE, "%" + search + "%");
            paramMap.put(SysUser.USER_TEL, "%" + search + "%");
            paramMap.put(SysUser.USER_EMAIL, "%" + search + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Long total = new NamedParameterJdbcTemplate(this.jdbcTemplate).queryForObject("select count(*)"+ sql, paramMap, Long.class);
        List<SysUser> resultList = new NamedParameterJdbcTemplate(this.jdbcTemplate)
                .query("select a.* " + QueryUtils.applySorting(sql.toString(), pageable.getSort(), "a") + " limit " + firstIdx + "," + pageSize, paramMap, new BeanPropertyRowMapper(SysUser.class));

        return new PageImpl<SysUser>(resultList, pageable, total);
    }
}
