
package com.ozh.core.repository.impl;

import com.ozh.core.repository.SysRoleRepositoryCustom;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.annotation.Resource;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysRoleRepositoryImpl implements SysRoleRepositoryCustom{

    @Resource
    private JdbcTemplate jdbcTemplate;

}
