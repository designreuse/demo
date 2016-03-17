package com.ozh.core.service;


import com.ozh.common.service.IBaseService;
import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.SysUser;

/**
 * (服务层类)
 * @author by ozh core generator
 * @version 1.0.0
 */
public interface SysUserService extends IBaseService<SysUser, Long> {

    SysUser registerUser(SysUser sysUser) throws BusinessException;

    SysUser login(String loginId, String password) throws BusinessException;

    void saveFrontSysUser(SysUser sysUser, Integer roleId) throws BusinessException;
}



