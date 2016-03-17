package com.ozh.core.service.impl;



import com.ozh.common.service.impl.AbstractBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ozh.core.entity.SysRole;
import com.ozh.core.repository.SysRoleRepository;
import com.ozh.core.service.SysRoleService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
public class SysRoleServiceImpl extends AbstractBaseService<SysRole, Long> implements SysRoleService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysRoleRepository getSysRoleRepository() {
		return (SysRoleRepository) baseRepository;
	}

}