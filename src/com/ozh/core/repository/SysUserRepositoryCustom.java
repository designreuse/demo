
package com.ozh.core.repository;

import com.ozh.core.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysUserRepositoryCustom{

    Page<SysUser> findUserList(Pageable pageable, String search);
}

