
package com.ozh.core.repository;

import com.ozh.common.repository.IBaseRepository;
import com.ozh.core.entity.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysUserRepository extends IBaseRepository<SysUser, Long>,SysUserRepositoryCustom {

    SysUser findByLoginId(String LoginId);

    List<SysUser> findByUserMobile(String userMobile);


}

