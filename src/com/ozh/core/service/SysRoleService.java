package com.ozh.core.service;


import com.ozh.common.service.IBaseService;
import com.ozh.core.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysRoleService extends IBaseService<SysRole, Long> {

    Page findSysRoleList(Pageable pageable,String search);

}
