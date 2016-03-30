
package com.ozh.core.repository.impl;

import com.ozh.core.entity.SysRole;
import com.ozh.core.repository.SysRoleRepositoryCustom;
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
public class SysRoleRepositoryImpl implements SysRoleRepositoryCustom{

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page findSysRoleList(Pageable pageable, String search) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from sys_role a where 1=1 ");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(search)) {
            sql.append(" AND a.SYS_ROLE_NM LIKE :sysRoleNm");
            paramMap.put(SysRole.SYS_ROLE_NM, "%" + search + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Long total = new NamedParameterJdbcTemplate(this.jdbcTemplate).queryForObject("select count(*)"+ sql, paramMap, Long.class);
        List<SysRole> resultList = new NamedParameterJdbcTemplate(this.jdbcTemplate)
                .query("select a.* " + QueryUtils.applySorting(sql.toString(), pageable.getSort(), "a") + " limit " + firstIdx + "," + pageSize, paramMap, new BeanPropertyRowMapper(SysRole.class));

        return new PageImpl<SysRole>(resultList, pageable, total);
    }
}
